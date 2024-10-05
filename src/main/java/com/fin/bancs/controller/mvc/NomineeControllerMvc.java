package com.fin.bancs.controller.mvc;

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
import com.fin.bancs.repository.Nominee_Repository;
import com.fin.bancs.services.NomineeDetailsServices;

@Controller
@RequestMapping("/nominee")
public class NomineeControllerMvc {
	@Autowired
	private Nominee_Repository nomRepo;
	@Autowired
	private NomineeDetailsServices nomineeService;

	
    @GetMapping("/add-nom") 
    public String modifyDetails(Model theModel,@RequestParam("owner_id") int owner_id){
    	NomineeDetails  nominee = new NomineeDetails();
    	//Optional<Nominee_Details>  nominee= nomRepo.findById(nominee_id);
//    	nominee.setOwner_id(owner_id);
//    	nominee.setOwner_type(1);
    	//if(!nominee.isEmpty() == true){
    	theModel.addAttribute("nominee",nominee);
    	//}
    	return "customer/Nominee";
    }
    
    @PostMapping("/insert")
    public String insertInto(@RequestBody NomineeDetails nominee){
    	//nomRepo.save(nominee);
    	NomineeDetails nominee_Details =new NomineeDetails();
    	
    	//nominee_Details=nomineeService.createModifyNomineeDetails(nominee,1);
    	return "redirect:/customer-service/getallcust";
    }
    
    @GetMapping("/show-nominee") 
    public String showNomineeDetails(Model theModel,@RequestParam("nominee_id") int nominee_id){
    	//Optional<Nominee_Details>  nominee = Optional.ofNullable(new Nominee_Details());
    	Optional<NomineeDetails>  nominee= nomRepo.findById(nominee_id);
    	if(nominee.isPresent()){
    	theModel.addAttribute("nominee",nominee);
    	}
    	
    	return "customer/Nominee";
    }


}
