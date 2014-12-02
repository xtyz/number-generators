package cz.pochoto.generator.model;

import java.util.List;
import java.util.Random;

public class Generator {
	private Integer seed;
	private List<Double> results;
	private Double root;
	private Double modulo;
	private Double addition;
	private Double first;
	private Double last;
	private String name;
	private Random random;
	
	public Generator(String name) {
		this.name = name;
	}

	public Double getModulo() {
		return modulo;
	}

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.last = Double.valueOf(seed);
		this.first = Double.valueOf(seed);
		this.seed = seed;
		this.random = new Random(seed);
	}

	public List<Double> getResults() {
		return results;
	}

	public void setResults(List<Double> results) {
		this.results = results;
	}

	public Double getRoot() {
		return root;
	}

	public void setRoot(Double root) {
		this.root = root;
	}

	public Double getAddition() {
		return addition;
	}

	public void setAddition(Double addition) {
		this.addition = addition;
	}

	public Double getFirst() {
		return first;
	}

	public void setFirst(Double first) {
		this.first = first;
	}

	public Double getLast() {
		return last;
	}

	public void setLast(Double last) {
		this.last = last;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModulo(Double modulo) {
		this.modulo = modulo;
	}
	
	public Random getRandom(){
		return random;
	}
}
