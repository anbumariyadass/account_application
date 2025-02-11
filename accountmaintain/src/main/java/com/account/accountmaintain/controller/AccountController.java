package com.account.accountmaintain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	@GetMapping("/publicendpoint")
	public String publicEndPoint() {
		return "Hi, Any one can access me";
	}
	
	@GetMapping("/privateendpoint")
	public String privateEndPoint() {
		return "Hi, You have been authourized to access this private end point.";
	}
	
}
