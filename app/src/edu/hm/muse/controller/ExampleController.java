package edu.hm.muse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExampleController {

	@RequestMapping(value = "/example.secu", method = RequestMethod.GET)
	public ModelAndView showSomeExample(
			HttpSession session,
			@RequestParam(value = "einname", required = false) String eineNamehier) {

		ModelAndView mv = new ModelAndView("examplepage");

		if (null != eineNamehier && !eineNamehier.isEmpty()) {
			mv.addObject("message", eineNamehier);

		} else {

			mv.addObject("message", "No input found");
		}

		return mv;
	}

	@RequestMapping(value = "/example.secu", method = RequestMethod.POST)
	public ModelAndView showSomeExamplePost(HttpSession session,
			@RequestParam(value = "einname", required = false) String eineIdhier) {

		ModelAndView mv = new ModelAndView("examplepage");

		if (null != eineIdhier && !eineIdhier.isEmpty()) {
			mv.addObject("message", eineIdhier + " at post");

		} else {

			mv.addObject("message", "No input found at post");
		}

		return mv;
	}
}
