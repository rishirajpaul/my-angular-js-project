package com.niit.Deskter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping("/test")
	public ModelAndView tt()
	{
		return new ModelAndView("test");
	}
	
}
