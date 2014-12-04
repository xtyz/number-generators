package cz.pochoto.generator;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cz.pochoto.generator.model.GeneratorCommand;
import cz.pochoto.generator.model.TestInput;
import cz.pochoto.generator.model.TestParam;
import cz.pochoto.generator.service.GeneratorService;
import cz.pochoto.generator.service.TestService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "generator", "type", "results", "outputGP", "outputKSE",
		"outputTP", "outputSRO", "outputR" })
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	List<Double> results = null;

	@Autowired
	private GeneratorService gaussianJavaGeneratorService;

	@Autowired
	private GeneratorService esslGeneratorService;

	@Autowired
	private GeneratorService sasGausslGeneratorService;

	@Autowired
	private GeneratorService integerJavaGeneratorService;

	@Autowired
	private GeneratorService doubleJavaGeneratorService;

	@Autowired
	private TestService growthPointTestService;

	@Autowired
	private TestService kolmogorovSmirnovEqualTestService;

	@Autowired
	private TestService tippingPointTestService;

	@Autowired
	private TestService spearmanRankOrderCorrelationTestService;

	@Autowired
	private TestService runTestService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		GeneratorCommand generator = new GeneratorCommand();
		int type = 1;
		model.addAttribute("generator", generator);
		model.addAttribute("type", type);
		return "home";
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String result(Model model,
			@ModelAttribute("generator") GeneratorCommand generator,
			@RequestParam(value = "type") int type) {

		switch (type) {
		case 1:
			results = gaussianJavaGeneratorService.getResults(
					generator.getCount(), generator.getSeed());
			break;
		case 2:
			results = integerJavaGeneratorService.getResults(
					generator.getCount(), generator.getSeed());
			break;
		case 3:
			results = doubleJavaGeneratorService.getResults(
					generator.getCount(), generator.getSeed());
			break;

		default:
			break;
		}

		model.addAttribute("results", results);

		TestInput input = new TestInput(((double) generator.getAlpha()) / 100,
				results);

		List<TestParam> outputGP = growthPointTestService.test(input);
		List<TestParam> outputKSE = kolmogorovSmirnovEqualTestService
				.test(input);
		List<TestParam> outputTP = tippingPointTestService.test(input);
		List<TestParam> outputR = runTestService.test(input);
		List<TestParam> outputSRO = spearmanRankOrderCorrelationTestService
				.test(input);

		model.addAttribute("outputGP", outputGP);
		model.addAttribute("outputKSE", outputKSE);
		model.addAttribute("outputTP", outputTP);
		model.addAttribute("outputSRO", outputSRO);
		model.addAttribute("outputR", outputR);

		return "home";
	}

	@RequestMapping(value = "/documentation", method = RequestMethod.GET)
	public String documentation(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "about";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "contact";
	}

}
