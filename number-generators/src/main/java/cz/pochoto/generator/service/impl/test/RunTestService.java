package cz.pochoto.generator.service.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.distribution.TDistribution;
import org.springframework.stereotype.Service;

import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;

@Service("runTestService")
public class RunTestService extends AbstractTestServiceImpl {

	@Override
	List<TestParam> solve(TestInput input) {
		final List<Double> data = input.getData();
		final Integer n = data.size();
		Double sum = 0D;
		for (final Double value : data) {
			sum += value;
		}
		final Double xp = sum / n;

		Integer n0 = 0;
		Integer n1 = 0;

		final List<Integer> runPoints = new ArrayList<Integer>(n);
		for (final Double value : data) {
			if (value > xp) {
				runPoints.add(1);
				n1 += 1;
			} else {
				runPoints.add(0);
				n0 += 1;
			}
		}

		Integer r = 0;
		final List<Integer> packets = new ArrayList<Integer>(n);
		packets.add(0); // First value is always 0
		for (int i = 1; i < n; i++) {

			final Integer valuePre = runPoints.get(i - 1);
			final Integer value = runPoints.get(i);

			if (!valuePre.equals(value)) {
				packets.add(1);
				r += 1;
			} else {
				packets.add(0);
			}
		}

		final Double doubleN = Double.valueOf(n);
		final Double er = 1D + ((2D * Double.valueOf(n0) * Double.valueOf(n1)) / doubleN);
		final Double dr = (2D * Double.valueOf(n0) * Double.valueOf(n1))
				* ((2D * Double.valueOf(n0) * Double.valueOf(n1)) - doubleN)
				/ (doubleN * doubleN * (doubleN - 1));

		// Count U
		final Double ubig = (r - er) / Math.sqrt(dr);

		// Count argument for u
		final Double uArg = 1 - (input.getAlpha() / 2);

		// Count u
		// Count u
		Double u;
		TDistribution tDist = new TDistribution(data.size());
		u = tDist.inverseCumulativeProbability(uArg);

		final Boolean h0Rejected = Math.abs(ubig) > u;

		List<TestParam> output = new ArrayList<TestParam>();
		output.add(new TestParam("SUM", sum.toString()));
		output.add(new TestParam("XP", xp.toString()));
		output.add(new TestParam("R", r.toString()));
		String points = "[";
		for (Integer i : runPoints) {
			points = points + i.toString() + ",";
		}
		points = points + "]";
		output.add(new TestParam("Run body", points));
		points = "[";
		for (Integer i : packets) {
			points = points + i.toString() + ",";
		}
		points = points + "]";
		output.add(new TestParam("Balíky", points));
		output.add(new TestParam("N1", n1.toString()));
		output.add(new TestParam("N0", n0.toString()));
		output.add(new TestParam("U", u.toString()));
		output.add(new TestParam("N", n.toString()));
		output.add(new TestParam("UBig", ubig.toString()));
		output.add(new TestParam("ER", er.toString()));
		output.add(new TestParam("DR", dr.toString()));
		output.add(new TestParam("Zamítá se", h0Rejected.toString()));

		return output;
	}

}
