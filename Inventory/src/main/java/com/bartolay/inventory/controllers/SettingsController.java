package com.bartolay.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.CurrencyForm;

@Controller
@RequestMapping(value="/settings")
public class SettingsController {

	@RequestMapping(value="/profile")
	public ModelAndView organizationProfile(ModelAndView mav) {
		return mav;
	}
	
	@RequestMapping(value="/currencies")
	public ModelAndView currencies(ModelAndView mav) {
		mav.setViewName("settings/index");
		mav.addObject("html", "currencies/list");
		mav.addObject("currencyForm", new CurrencyForm());
		mav.addObject("settings", true);
		return mav;
	}
}
