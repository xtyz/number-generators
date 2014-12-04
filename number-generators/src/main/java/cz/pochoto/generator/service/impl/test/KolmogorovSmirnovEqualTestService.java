package cz.pochoto.generator.service.impl.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;

@Service("kolmogorovSmirnovEqualTestService")
public class KolmogorovSmirnovEqualTestService extends AbstractTestServiceImpl {

	@Override
	List<TestParam> solve(TestInput input) {
		final List<Double> data = input.getData();
		final Integer n = data.size();
		final Double alpha = input.getAlpha();

		final Double dBig = Math.sqrt((1D / (2D * Double.valueOf(n)))
				* Math.log(2D / alpha));

		final List<Interval> intervals = new ArrayList<KolmogorovSmirnovEqualTestService.Interval>(
				10);
		final Double step = 1D / Double.valueOf(10);
		for (int i = 0; i < 10; i++) {
			final Double min = i * step;
			final Double max = min + step;
			intervals.add(new Interval(min, max));
		}

		Double previuousTeoreticalDistributionValue = 0D;
		final Double iterateTeoretical = Double.valueOf(n) / 10;
		final Map<Interval, Double> teoreticalDistribution = new HashMap<KolmogorovSmirnovEqualTestService.Interval, Double>();
		for (final Interval interval : intervals) {
			previuousTeoreticalDistributionValue = previuousTeoreticalDistributionValue
					+ iterateTeoretical;
			teoreticalDistribution.put(interval,
					previuousTeoreticalDistributionValue / n);
		}

		Double previuousValue = 0D;
		final Map<Interval, Double> empiricDistribution = new HashMap<KolmogorovSmirnovEqualTestService.Interval, Double>();
		for (final Interval interval : intervals) {
			Double iterateEmpiric = 0D;
			for (final Double value : data) {
				if (interval.contains(value)) {
					iterateEmpiric += 1;
				}
			}
			previuousValue = previuousValue + iterateEmpiric;
			empiricDistribution.put(interval, previuousValue / n);
		}
		Double maxDifference = null;

		for (final Interval interval : intervals) {

			final Double empiric = empiricDistribution.get(interval);
			final Double teoretical = teoreticalDistribution.get(interval);

			final Double diff = empiric - teoretical;

			if (maxDifference == null) {
				maxDifference = diff;
			} else if (maxDifference < diff) {
				maxDifference = diff;
			}
		}
		maxDifference = Math.abs(maxDifference);

		final Boolean h0Rejected = maxDifference > dBig;

		List<TestParam> output = new ArrayList<TestParam>();

		output.add(new TestParam("N", n.toString()));
		output.add(new TestParam("D(n,a)", dBig.toString()));
		output.add(new TestParam("D", maxDifference.toString()));
		output.add(new TestParam("Zamítá se", h0Rejected.toString()));

		return output;
	}

	private class Interval {
		private final Double min;
		private final Double max;

		public Interval(final Double min, final Double max) {
			super();
			this.min = min;
			this.max = max;
		}

		public Boolean contains(final Double value) {
			return min < value && value <= max;
		}

	}

}
