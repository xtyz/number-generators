package cz.pochoto.generator.service.impl;

public abstract class AbstractFibonacciAdditiveGeneratorService extends DefaultGeneratorService{

	public Double generate(){		
		final Double actual = ((generator.getRoot() * generator.getLast()) + generator.getFirst()) % generator.getModulo();
		generator.setLast(actual);
		return actual / generator.getModulo();
	}

}
