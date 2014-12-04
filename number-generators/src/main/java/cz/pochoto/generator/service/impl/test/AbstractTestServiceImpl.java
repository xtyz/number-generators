package cz.pochoto.generator.service.impl.test;

import java.util.List;

import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;
import cz.pochoto.generator.service.TestService;

public abstract class AbstractTestServiceImpl implements TestService {

	@Override
	public List<TestParam> test(TestInput input) {
		return solve(input);
	}

	abstract List<TestParam> solve(TestInput input);

}
