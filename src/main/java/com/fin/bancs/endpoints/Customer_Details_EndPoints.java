package com.fin.bancs.endpoints;

import com.fin.bancs.BP.CustomerID;
import com.fin.bancs.BP.Customer_Details;
import com.fin.bancs.BP.Nominee_Details;
import com.fin.bancs.repository.Customer_Details_Repository;
import com.fin.bancs.services.Customer_Details_services;

import jakarta.persistence.EntityManager;

import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer-service")
public class Customer_Details_EndPoints {
    @Autowired
    private Customer_Details_Repository detailsRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    Customer_Details_services customerDetailsController;

    @GetMapping("/getallcust")
    public String getAllCustomer(Model theModel){
    	//List<Customer_Details> allCust= customerDetailsController.getAllCust();
    	CustomerID prim= new CustomerID();
    	prim.setCUS_ID(52);
    	prim.setCUS_TYP(12);
    	Customer_Details customer_Details= entityManager.find(Customer_Details.class, prim);

    	List<Customer_Details> allCust =detailsRepository.findAll();
    	for (Customer_Details details: allCust) {
    		System.out.println(details.toString());
    	}
    	System.out.println(customer_Details);
    	theModel.addAttribute("Customer_Details",allCust);
        return "customer/all-Customer-details";
    }
    @PostMapping("/create-customer")
    public Customer_Details creteCust(@RequestParam Customer_Details inp_customerDetails){
        customerDetailsController.CreateCustDetails(inp_customerDetails);
        return inp_customerDetails;
    }
    @GetMapping("/add-cust") 
    public String insertDetails(Model theModel){
    	Customer_Details customer_Details= new Customer_Details();
    	theModel.addAttribute("customer",customer_Details);
        //return "customer/add-customer";
    	return "customer/index-form";
    }
    @GetMapping("/modify-cust") 
    public String modifyDetails(Model theModel,@RequestParam("cust-id") int cust_id){
    	CustomerID customer_id= new CustomerID();
    	customer_id.setCUS_ID(cust_id);
    	customer_id.setCUS_TYP(12);
    	Optional<Customer_Details> customer_Details= detailsRepository.findById(customer_id);
    	theModel.addAttribute("customer",customer_Details);
        //return "customer/add-customer";
    	return "customer/index-form";
    }
    
    @PostMapping("/insert")
    public String insertInto(@ModelAttribute("customer") Customer_Details customer_Details){
//        detailsRepository.save(customer_Details);
        customerDetailsController.CreateCustDetails(customer_Details);
        return "redirect:/customer-service/getallcust";
    }
}



