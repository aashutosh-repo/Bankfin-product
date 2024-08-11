package com.fin.bancs.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.customer.NomineeDetails;
import com.fin.bancs.dto.NomineeDto;
import com.fin.bancs.repository.Nominee_Repository;
import com.fin.bancs.services.NomineeDetailsServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Omega Bank Nominee Controller",
description = "This Swagger containing CRUD operation for Nominee Management")
@RestController
@RequestMapping("/customer/nominee")
public class NomineeController {
	@Autowired
	private Nominee_Repository nomRepo;
	@Autowired
	private NomineeDetailsServices nomineeService;

	
    @GetMapping("/add-nom") 
    public String modifyDetails(@RequestParam int owner_id){
    	NomineeDetails  nominees = new NomineeDetails();
    	Optional<NomineeDetails>  nominee= nomRepo.findById(owner_id); //Check
    	
//    	nominee.setOwner_id(owner_id);
//    	nominee.setOwner_type(1);
    	return "";
    
    }
    
    @PostMapping("/insert")
    public String insertInto(@RequestBody NomineeDto nominee){
    	//nomRepo.save(nominee);
    	NomineeDetails nominee_Details =new NomineeDetails();
    	
    	nominee_Details=nomineeService.createModifyNomineeDetails(nominee,1);
    	return "redirect:/customer-service/getallcust";
    }
    
    @GetMapping("/show-nominee") 
    public String showNomineeDetails(@RequestParam int nominee_id){
    	//Optional<Nominee_Details>  nominee = Optional.ofNullable(new Nominee_Details());
    	Optional<NomineeDetails>  nominee= nomRepo.findById(nominee_id);
    	if(!nominee.isEmpty()){
    		return nominee.get().toString();
    	}
    	
    	return null;
    }


}
