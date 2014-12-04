package cz.pochoto.generator.service.impl;

import org.springframework.stereotype.Service;

@Service("doubleJavaGeneratorService")
public class DoubleJavaGeneratorService extends AbstractGeneratorServiceImpl {

	@Override
	Double generate() {
		return generator.getRandom().nextDouble();
	}

	@Override
	String setGeneratorName() {
		return "Double Java Generator";
	}

}
