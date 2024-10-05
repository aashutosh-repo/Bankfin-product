package com.fin.bancs.controller;

import java.util.ArrayList;
import java.util.List;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create Nominee API",
    description = "REST API to create Nominee in Omega Bank")
    @ApiResponse(
            responseCode = "201",
            description = "Http Status CREATED",
            links = @Link()
    )    
    @PostMapping("/insert")
    public List<NomineeDetails> insertInto(@RequestBody List<NomineeDto> nominee){
    	//nomRepo.save(nominee);
    	List<NomineeDetails> nominee_Details;
    	
    	nominee_Details=nomineeService.createNomineesDetails(nominee,1);
    	return nominee_Details;
    }
    
    @GetMapping("/show-nominee") 
    public String showNomineeDetails(@RequestParam(name = "nominee_id") int nomineeId){
    	//Optional<Nominee_Details>  nominee = Optional.ofNullable(new Nominee_Details());
    	Optional<NomineeDetails>  nominee= nomRepo.findById(nomineeId);
    	if(!nominee.isEmpty()){
    		return nominee.get().toString();
    	}
    	
    	return "No Data Found";
    }
    
    @GetMapping("/nomineeByOwnerId") 
    public List<NomineeDetails> showNomineeDetailsByOwnerId(@RequestParam int OwnerId){
    	List<NomineeDetails>  nominees= nomineeService.findNomineeByOwnerId(OwnerId);
    	
    	return nominees;
    }


}
