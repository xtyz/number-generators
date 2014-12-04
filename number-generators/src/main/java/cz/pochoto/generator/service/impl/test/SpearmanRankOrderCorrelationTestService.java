package cz.pochoto.generator.service.impl.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.distribution.TDistribution;
import org.springframework.stereotype.Service;

import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;

@Service("spearmanRankOrderCorrelationTestService")
public class SpearmanRankOrderCorrelationTestService extends
		AbstractTestServiceImpl {

	@Override
	List<TestParam> solve(TestInput input) {
		// Preper ordered lists
		final List<Double> orderedByIndex = input.getData();
		final List<Double> orderedBySize = create(orderedByIndex); // Ascending

		// Find n
		final Integer n = orderedByIndex.size();

		// Prepare target objects
		final List<Double> dPowTwos = new ArrayList<Double>(n);
		Double dPowTwoSum = Double.valueOf(0);

		// For each value
		for (final Double value : orderedByIndex) {

			// Find index of order
			final Integer index = orderedByIndex.indexOf(value) + 1;

			// Find index of size
			final Integer size = n - orderedBySize.indexOf(value);

			// Count d^2
			final Double dPowTwo = Math.pow((index - size), 2);
			dPowTwos.add(dPowTwo);
			dPowTwoSum += dPowTwo;
		}

		// count rs
		final Double rs = 1 - ((6 / (n * (Math.pow(n, 2) - 1))) * (dPowTwoSum));

		// count us
		final Double us = Math.abs(rs) * (Math.sqrt(n - 1));

		// Count argument for u
		final Double uArg = 1 - (input.getAlpha() / 2);

		// Count u
		Double u;
		TDistribution tDist = new TDistribution(orderedByIndex.size());
		u = tDist.inverseCumulativeProbability(uArg);

		final Boolean h0Rejected = Math.abs(us) > u;

		List<TestParam> output = new ArrayList<TestParam>();
		output.add(new TestParam("RS", rs.toString()));
		output.add(new TestParam("US", us.toString()));
		output.add(new TestParam("U", u.toString()));
		output.add(new TestParam("N", n.toString()));
		output.add(new TestParam("Zamítá se", h0Rejected.toString()));

		return output;
	}

	private List<Double> create(final List<Double> orderedBySize) {

		final List<Double> result = new ArrayList<Double>(orderedBySize);
		Collections.sort(result);
		return result;
	}

}
