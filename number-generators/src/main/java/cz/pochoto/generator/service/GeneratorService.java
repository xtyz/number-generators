package cz.pochoto.generator.service;

import java.util.List;

public interface GeneratorService {
	
	public String getGeneratorName();
	
	public List<Double> getResults(Integer count, Integer seed);
}
