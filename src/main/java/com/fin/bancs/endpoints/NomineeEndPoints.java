package com.fin.bancs.endpoints;

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
    public String modifyDetails(Model theModel,@RequestParam("owner_id") int owner_id){
    	Nominee_Details  nominee = new Nominee_Details();
    	//Optional<Nominee_Details>  nominee= nomRepo.findById(nominee_id);
    	nominee.setOwner_id(owner_id);
    	nominee.setOwner_type(1);
    	//if(!nominee.isEmpty() == true){
    	theModel.addAttribute("nominee",nominee);
    	//}
    	return "customer/Nominee";
    }
    
    @PostMapping("/insert")
    public String insertInto(@RequestBody Nominee_Details nominee){
    	//nomRepo.save(nominee);
    	Nominee_Details nominee_Details =new Nominee_Details();
    	
    	nominee_Details=nomineeService.createModifyNomineeDetails(nominee,1);
    	return "redirect:/customer-service/getallcust";
    }
    
    @GetMapping("/show-nominee") 
    public String showNomineeDetails(Model theModel,@RequestParam("nominee_id") int nominee_id){
    	//Optional<Nominee_Details>  nominee = Optional.ofNullable(new Nominee_Details());
    	Optional<Nominee_Details>  nominee= nomRepo.findById(nominee_id);
    	if(!nominee.isEmpty() == true){
    	theModel.addAttribute("nominee",nominee);
    	}
    	
    	return "customer/Nominee";
    }


}
