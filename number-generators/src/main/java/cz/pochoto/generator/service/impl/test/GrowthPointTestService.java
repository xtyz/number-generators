package cz.pochoto.generator.service.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.distribution.TDistribution;
import org.springframework.stereotype.Service;

import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;

@Service("growthPointTestService")
public class GrowthPointTestService extends AbstractTestServiceImpl {

	@Override
	List<TestParam> solve(TestInput input) {
		final List<Double> data = input.getData();
		final Integer n = data.size();
		final List<Integer> growthPoints = new ArrayList<Integer>(n);
		final Double ec = (1D / 2D) * (Double.valueOf(n) - 1D);
		final Double dc = (Double.valueOf(n) + 1) / 12;

		Integer c = 0;

		for (int i = 0; i < n - 1; i++) {
			final Double value = data.get(i);
			final Double valueSuc = data.get(i + 1);

			if (value < valueSuc) {
				growthPoints.add(1);
				c += 1;
			} else {
				growthPoints.add(0);
			}
		}

		// Count U
		final Double ubig = (c - ec) / Math.sqrt(dc);

		// Count argument for u
		final Double uArg = 1 - (input.getAlpha() / 2);

		// Count u
		Double u;
		TDistribution tDist = new TDistribution(data.size());
		u = tDist.inverseCumulativeProbability(uArg);

		final Boolean h0Rejected = Math.abs(ubig) > u;

		List<TestParam> output = new ArrayList<TestParam>();
		output.add(new TestParam("U", u.toString()));
		output.add(new TestParam("N", n.toString()));
		output.add(new TestParam("UBig", ubig.toString()));
		output.add(new TestParam("C", c.toString()));
		output.add(new TestParam("EC", ec.toString()));
		output.add(new TestParam("DC", dc.toString()));
		String points = "[";
		for (Integer i : growthPoints) {
			points = points + i.toString() + ",";
		}
		points = points + "]";
		output.add(new TestParam("Body rùstu", points));
		output.add(new TestParam("Zamítá se", h0Rejected.toString()));

		return output;
	}

}
