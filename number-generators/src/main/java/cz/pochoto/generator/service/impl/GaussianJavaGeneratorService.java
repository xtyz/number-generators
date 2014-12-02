package cz.pochoto.generator.service.impl;

public class GaussianJavaGeneratorService extends DefaultGeneratorService {

	@Override
	Double generate() {
		return generator.getRandom().nextGaussian();
	}

}
