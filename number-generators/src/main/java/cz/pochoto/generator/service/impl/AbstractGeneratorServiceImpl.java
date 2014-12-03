package cz.pochoto.generator.service.impl;

import java.util.ArrayList;
import java.util.List;

import cz.pochoto.generator.model.Generator;
import cz.pochoto.generator.service.GeneratorService;

public abstract class AbstractGeneratorServiceImpl implements GeneratorService {

	protected Generator generator = new Generator("");

	@Override
	public String getGeneratorName() {
		return generator.getName();
	}

	@Override
	public List<Double> getResults(Integer count, Integer seed) {
		List<Double> results = new ArrayList<Double>(count);
		generator.setName(setGeneratorName());
		generator.setSeed(seed);
		for (int i = 0; i < count; i++) {
			results.add(generate());
		}
		return results;
	}

	abstract Double generate();

	abstract String setGeneratorName();
}
