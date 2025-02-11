package com.account.accountmaintain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.accountmaintain.entity.Account;
import com.account.accountmaintain.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public String addExpense(Account account) {
		accountRepository.save(account);
		return "Expense detail for "+account.getExpenseName()+" is added to successfully";
	}
	
	public List<Account> getAllExpenses() {		
		return accountRepository.findAll();
	}
	
	public String updateExpense(Long id, Account account) {
	    Optional<Account> expenseOptional = accountRepository.findById(id);
	    if (expenseOptional.isPresent()) {
	    	Account updatedAccount = expenseOptional.get();
	    	updatedAccount.setExpenseName(account.getExpenseName());
	    	updatedAccount.setAmount(account.getAmount());
	    	accountRepository.save(updatedAccount);
	    	return "Record updated successfully....";
	    } else {
	    	return "Given id is not able to be found in the DB. Pleae check your input...";
	    }
	}
	
	public String deleteExpense(Long id) {
		accountRepository.deleteById(id);
		return "Record deleted successfully....";
	}
}
