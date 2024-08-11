package com.fin.bancs.controller.mvc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.bancs.customer.CustomerID;
import com.fin.bancs.customer.CustomerDetails;
import com.fin.bancs.dto.CustomerDto;
import com.fin.bancs.repository.Customer_Details_Repository;
import com.fin.bancs.services.CustomerDetailsServices;

import jakarta.persistence.EntityManager;

@Controller
@RequestMapping("/customer-service")
public class Customer_Details_ControllerMvc {
    @Autowired
    private Customer_Details_Repository detailsRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    CustomerDetailsServices customerDetailsController;

    @GetMapping("/getallcust")
    public String getAllCustomer(Model theModel){
    	//List<Customer_Details> allCust= customerDetailsController.getAllCust();
    	CustomerID prim= new CustomerID();
    	CustomerDetails customer_Details= entityManager.find(CustomerDetails.class, prim);

    	List<CustomerDetails> allCust =detailsRepository.findAll();
    	for (CustomerDetails details: allCust) {
    		System.out.println(details.toString());
    	}
    	System.out.println(customer_Details);
    	theModel.addAttribute("Customer_Details",allCust);
        return "customer/all-Customer-details";
    }
    @PostMapping("/create-customer")
    public CustomerDto creteCust(@RequestParam CustomerDto inp_customerDetails){
        //customerDetailsController.CreateCustDetails(inp_customerDetails);
        return inp_customerDetails;
    }
    @GetMapping("/add-cust") 
    public String insertDetails(Model theModel){
    	CustomerDetails customer_Details= new CustomerDetails();
    	theModel.addAttribute("customer",customer_Details);
        //return "customer/add-customer";
    	return "customer/index-form";
    }
    @GetMapping("/modify-cust") 
    public String modifyDetails(Model theModel,@RequestParam("cust-id") int cust_id){
    	CustomerID customer_id= new CustomerID();
//    	customer_id.setCUS_ID(cust_id);
//    	customer_id.setCUS_TYP(12);
    	Optional<CustomerDetails> customer_Details= detailsRepository.findById(customer_id);
    	theModel.addAttribute("customer",customer_Details);
        //return "customer/add-customer";
    	return "customer/index-form";
    }
    
    @PostMapping("/insert")
    public String insertInto(@ModelAttribute("customer") CustomerDto customer_Details){
//        detailsRepository.save(customer_Details);
        //customerDetailsController.CreateCustDetails(customer_Details);
        return "redirect:/customer-service/getallcust";
    }
}



