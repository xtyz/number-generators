package cz.pochoto.generator.service;

import java.util.List;

import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;

public interface TestService {

	public List<TestParam> test(final TestInput input);

}
