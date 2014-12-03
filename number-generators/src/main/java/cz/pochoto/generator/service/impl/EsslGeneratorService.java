package cz.pochoto.generator.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("esslGeneratorService")
public class EsslGeneratorService extends
		AbstractLehmerMultiplicativeGeneratorService {

	@Override
	public List<Double> getResults(Integer count, Integer seed) {
		generator.setRoot(Double.valueOf(16807));
		generator.setModulo(Math.pow(2, 31) - 1);
		return super.getResults(count, seed);
	}

	@Override
	String setGeneratorName() {
		return "Essl Generator";
	}

}
