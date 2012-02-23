/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.temp.service.AccountService;

@Controller
public class LoginController
{
	@Autowired
	private AccountService accountService ;
    
//	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//	}
	
	@RequestMapping(value = "/login")
	public String login()
	{
    	if (accountService.isLoggedIn())
    	    return "redirect:/req/myaccount" ;
    	else
    		return "login" ;
	}
	
    @RequestMapping(value = "/account/newaccount", method = RequestMethod.GET)
    public String newaccount()
    {
    	return "account" ;
    }
		
	@RequestMapping(value="/account/makeaccount", method=RequestMethod.POST)
	public String makeaccount(@RequestParam String username, String password, HttpServletResponse response)
	{
		String result = null ;
	    if (username == null || username.isEmpty()) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        result = "Username is not entered!";
	    }
	    
	    if (password == null || password.isEmpty()) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        result = "Password is not entered!";
	    }
	    
	    User user = accountService.findByLogin(username) ;
	    if (user == null) {
	    	accountService.createUserAccount(username, password) ;
	        response.setStatus(HttpServletResponse.SC_OK) ;
	        result = String.format("Account %s is created.", username) ;
	    } else {
	    	result = username + " is not available." ;
	    }
	    
	    return "redirect:/account/accountresult/" + result ;
	}
	
	@RequestMapping(value="/account/accountresult/{output}", method=RequestMethod.GET)
	public String accountresult(@PathVariable String output, Model model)
	{
    	model.addAttribute("output", output) ;
		return "account_result" ;
	}
}
