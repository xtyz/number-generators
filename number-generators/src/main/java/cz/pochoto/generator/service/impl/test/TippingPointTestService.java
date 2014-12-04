package cz.pochoto.generator.service.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.distribution.TDistribution;
import org.springframework.stereotype.Service;

import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;

@Service("tippingPointTestService")
public class TippingPointTestService extends AbstractTestServiceImpl {

	@Override
	List<TestParam> solve(TestInput input) {
		final List<Double> data = input.getData();
		final Integer n = data.size();
		final List<Integer> trippingPoints = new ArrayList<Integer>(n - 2);
		final Double ep = (2D / 3D) * (Double.valueOf(n) - 2D);
		final Double dp = (16 * Double.valueOf(n) - 29) / 90;

		Integer p = 0;

		for (int i = 1; i < n - 1; i++) {
			final Double valuePre = data.get(i - 1);
			final Double value = data.get(i);
			final Double valueSuc = data.get(i + 1);

			final Double coef = (valuePre - value) * (valueSuc - value);

			if (coef > 0) {
				trippingPoints.add(1);
				p += 1;
			} else {
				trippingPoints.add(0);
			}
		}

		// Count U
		final Double ubig = (p - ep) / Math.sqrt(dp);

		// Count argument for u
		final Double uArg = 1 - (input.getAlpha() / 2);

		// Count u
		// Count u
		Double u;
		TDistribution tDist = new TDistribution(data.size());
		u = tDist.inverseCumulativeProbability(uArg);

		final Boolean h0Rejected = Math.abs(ubig) > u;

		List<TestParam> output = new ArrayList<TestParam>();
		output.add(new TestParam("U", u.toString()));
		output.add(new TestParam("N", n.toString()));
		output.add(new TestParam("UBig", ubig.toString()));
		output.add(new TestParam("P", p.toString()));
		output.add(new TestParam("DP", dp.toString()));
		String points = "[";
		for (Integer i : trippingPoints) {
			points = points + i.toString() + ",";
		}
		points = points + "]";
		output.add(new TestParam("Body zvratu", points));
		output.add(new TestParam("Zamítá se", h0Rejected.toString()));

		return output;
	}

}
