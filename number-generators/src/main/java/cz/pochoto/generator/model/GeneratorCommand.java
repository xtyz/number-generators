package cz.pochoto.generator.model;

public class GeneratorCommand {
	private int count = 1000;
	private int seed = 66;
	private int alpha = 5;

	public GeneratorCommand() {
		super();
	}

	public GeneratorCommand(int count, int seed, int alpha) {
		super();
		this.count = count;
		this.seed = seed;
		this.alpha = alpha;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

}
