package com.tm.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * value rest controller
 * 
 * @author Park SeongMin
 *
 */
@RestController
@RequestMapping("/value-calc")
public class ValueCalculateController {

	@RequestMapping(method=RequestMethod.POST)
	public void calculateValue() {
		
	}
	
}
