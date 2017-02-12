package com.tm.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tm.shop.service.ValueCalculateService;

/**
 * value rest controller
 * 
 * @author Park SeongMin
 *
 */
@RestController
@RequestMapping("/values-calc")
public class ValueCalculateController {

	@Autowired
	private ValueCalculateService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public void calculateValue() {
		service.calculate();
		
	}
	
}
