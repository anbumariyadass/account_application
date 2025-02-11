package com.account.accountmaintain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.accountmaintain.entity.Account;
import com.account.accountmaintain.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService accountService;

	@GetMapping("/publicendpoint")
	public String publicEndPoint() {
		return "Hi, Any one can access me";
	}
	
	@GetMapping("/privateendpoint")
	public String privateEndPoint() {
		return "Hi, You have been authourized to access this private end point.";
	}
	
	
	@PostMapping("/addexpense")
	@PreAuthorize("hasRole('ACCOUNTANT')")
	public String addExpense(@RequestBody Account account) {
		return accountService.addExpense(account);
	}
	
	@GetMapping("allexpenses")
	@PreAuthorize("hasAnyRole('ACCOUNTANT', 'ADMIN')")
	public List<Account> getAllExpenses() {
		return accountService.getAllExpenses();
	}
	
	@PutMapping("/updateexpense/{id}")
	@PreAuthorize("hasRole('ACCOUNTANT')")
	public String updateExpense(@PathVariable Long id, @RequestBody Account account) {		
		return accountService.updateExpense(id, account);
	}
	
	@DeleteMapping("/deleteexpense/{id}")
	@PreAuthorize("hasRole('ACCOUNTANT')")
	public String deleteExpense(@PathVariable Long id) {		
		return accountService.deleteExpense(id);
	}
	
}
