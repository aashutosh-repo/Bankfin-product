package com.fin.bancs.controller.mvc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.bancs.account.Account;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.services.Account_services;

@Controller
@RequestMapping("mvc/account")
public class Account_Controller_Mvc {
	@Autowired
	public Account_repository acc_repo;
	@Autowired
	public Account_services acc_services;
	
	
    @GetMapping("/view")
    public String viewAccountDetails(Model theModel){    

    	List<Account> allAccount =acc_repo.findAll();
    	for (Account details: allAccount) {
    		System.out.println(details.toString());
    	}
    	
    	theModel.addAttribute("Account",allAccount);
        return "mainpage/tiles";
    }
    
    @GetMapping("/create")
    public void createAccount(@RequestBody Account account) {
    	//acc_services.ceateModifyAccountDetails(account, 0);
    }
    @GetMapping("/modify")
    public void modifyAccount(@RequestBody Account account) {
    	//acc_services.ceateModifyAccountDetails(account, 1);
    }
}
