/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/")
	public String home() {
		logger.info("HomeController: Passing Through...");
		return "home" ;
	}
	
	@RequestMapping(value = "/req/myaccount")
	public String myAccount() {
		return "myaccount" ;
	}
}
