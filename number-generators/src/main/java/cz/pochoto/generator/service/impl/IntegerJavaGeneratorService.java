package cz.pochoto.generator.service.impl;

import org.springframework.stereotype.Service;

@Service("integerJavaGeneratorService")
public class IntegerJavaGeneratorService extends AbstractGeneratorServiceImpl {

	@Override
	Double generate() {
		return (double) generator.getRandom().nextInt();
	}

	@Override
	String setGeneratorName() {
		return "Integer Java Generator";
	}

}
