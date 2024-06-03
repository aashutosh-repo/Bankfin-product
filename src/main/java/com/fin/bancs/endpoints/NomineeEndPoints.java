package com.fin.bancs.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.BP.Nominee_Details;
import com.fin.bancs.controller.Nominee_Details_Controller;
import com.fin.bancs.repository.Nominee_Repository;
import com.fin.bancs.services.Nominee_Details_services;

@Controller
@RequestMapping("/nominee")
public class NomineeEndPoints {
	@Autowired
	private Nominee_Repository nomRepo;
	@Autowired
	private Nominee_Details_Controller nomineeService;
    private static final Logger logger = LoggerFactory.getLogger(NomineeEndPoints.class);

	
    @GetMapping("/add-nom") 
    public String modifyDetails(Model theModel){
    	Nominee_Details  nominee = new Nominee_Details();
    	theModel.addAttribute("nominee",nominee);
    	return "customer/Nominee";
    }
    
    @PostMapping("/insert")
    public String insertInto(@RequestBody Nominee_Details nominee){
    	//nomRepo.save(nominee);
    	Nominee_Details nominee_Details =new Nominee_Details();
    	
    	nominee_Details=nomineeService.createModifyNomineeDetails(nominee,1);
    	return "redirect:/customer-service/getallcust";
    }


}
