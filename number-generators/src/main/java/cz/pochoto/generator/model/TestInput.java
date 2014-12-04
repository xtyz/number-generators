package cz.pochoto.generator.model;

import java.util.List;

/**
 * @author Tomáš Pochobradský
 */
public class TestInput {

	private final Double alpha;

	private final List<Double> data;

	public TestInput(final Double alpha, final List<Double> data) {
		super();
		this.alpha = alpha;
		this.data = data;
	}

	public Double getAlpha() {
		return alpha;
	}

	public List<Double> getData() {
		return data;
	}

}
