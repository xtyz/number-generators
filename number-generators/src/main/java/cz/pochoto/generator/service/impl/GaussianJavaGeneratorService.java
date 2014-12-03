package cz.pochoto.generator.service.impl;

import org.springframework.stereotype.Service;

@Service("gaussianJavaGeneratorService")
public class GaussianJavaGeneratorService extends AbstractGeneratorServiceImpl {

	@Override
	Double generate() {
		return generator.getRandom().nextGaussian();
	}

	@Override
	String setGeneratorName() {
		return "Gaussian Java Generator";
	}

}
