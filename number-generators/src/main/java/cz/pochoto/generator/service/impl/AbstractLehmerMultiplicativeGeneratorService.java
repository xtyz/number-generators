package cz.pochoto.generator.service.impl;

public abstract class AbstractLehmerMultiplicativeGeneratorService extends DefaultGeneratorService {
	
	public Double generate(){
		final Double actual = (generator.getRoot() * generator.getLast()) % generator.getModulo();
		generator.setLast(actual);
		return actual / generator.getModulo();
	}

}
