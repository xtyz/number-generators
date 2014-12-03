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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.pochoto.generator.service.GeneratorService;
import cz.pochoto.generator.service.TestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

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

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		List<Double> gaussianResults = gaussianJavaGeneratorService.getResults(
				1000, 66);
		List<Double> integerRresults = integerJavaGeneratorService.getResults(
				1000, 66);
		List<Double> doubleResults = doubleJavaGeneratorService.getResults(
				1000, 66);

		List<Double> results2 = esslGeneratorService.getResults(1000, 66);
		List<Double> results3 = sasGausslGeneratorService.getResults(1000, 66);

		model.addAttribute("serverTime", formattedDate);

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
