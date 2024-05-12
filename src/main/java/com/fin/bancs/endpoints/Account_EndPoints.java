package com.fin.bancs.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.bancs.AM.Account;
import com.fin.bancs.BP.CustomerID;
import com.fin.bancs.BP.Customer_Details;
import com.fin.bancs.repository.Account_repository;

@Controller
@RequestMapping("/account")
public class Account_EndPoints {
	@Autowired
	public Account_repository acc_repo;
	
    @GetMapping("/view")
    public String viewAccountDetails(Model theModel){    

    	List<Account> allAccount =acc_repo.findAll();
    	for (Account details: allAccount) {
    		System.out.println(details.toString());
    	}
    	
    	theModel.addAttribute("Account",allAccount);
        return "account/tiles";
    }
}
