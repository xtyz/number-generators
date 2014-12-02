package cz.pochoto.generator.service.impl;

import java.util.List;

public class EsslGeneratorService extends AbstractLehmerMultiplicativeGeneratorService {

	public List<Double> getResults(Integer count, Integer seed) {
		generator.setRoot(Double.valueOf(16807));
		generator.setModulo(Math.pow(2, 31) - 1);
		return super.getResults(count, seed);		
	}

}
