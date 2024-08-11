package com.fin.bancs.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.bancs.customer.Nominee_Details;
import com.fin.bancs.repository.Nominee_Repository;
import com.fin.bancs.services.si.Nominee_Service_Interface;

@Service
public class Nominee_Details_services implements Nominee_Service_Interface{

    @Autowired
    private Nominee_Repository nomineeRepository;


    @Override
	public Nominee_Details createModifyNomineeDetails(Nominee_Details nomineeDetails,int flag){
		//Flag=1-> Create and flag=2 -> Modify
		Nominee_Details nominee_details = new Nominee_Details();
		int NomineeId= nomineeDetails.getNom_add_id();
		if(flag==2) {
			//check if customer exist then proceed else throw Error "Customer Not Exist"
			if(nomineeRepository.findById(NomineeId) != null) {
				nomineeRepository.save(nomineeDetails);
			}
		}

		nominee_details.setNom_add_id(999);
		nominee_details.setOwner_id(nomineeDetails.getOwner_id());
		nominee_details.setNom_type(nomineeDetails.getNom_type());
		nominee_details.setNom_add_id(nomineeDetails.getNom_add_id());
		nominee_details.setNom_type(nomineeDetails.getNom_add_id());
		nominee_details.setNom_type_code(00000); //Add this After putting data in Golden_Codes
		//nominee_details.setNOMINEE_FIRST_NAME(nomineeDetails.getNOMINEE_FIRST_NAME());
		nominee_details.setNominee_middle_name(nomineeDetails.getNominee_middle_name());
		nominee_details.setNominee_last_name(nomineeDetails.getNominee_last_name());
		nominee_details.setRtln_type(nomineeDetails.getRtln_type());
		nominee_details.setNom_type_code(0000); //Add this After putting data in Golden_Codes
		nominee_details.setDate_of_birth(nomineeDetails.getDate_of_birth());
		nominee_details.setNom_add_id(999); //address details should also come as a input 
		nominee_details.setNom_doc_id("999"); //Documents details should also come as a input
		//Need to call 2 task Address creation and Document Creation task here 
		int nomineee_id=1;
		//nomineeRepository.findBy(null, null);
		if(flag==1) {
		nomineeRepository.save(nominee_details);
		}else {
			Nominee_Details nominee_detail = nomineeRepository.save(nominee_details);
		}
        Optional<Nominee_Details> nominee = nomineeRepository.findById(nomineee_id);
        if(nominee != null){
		}
		return nominee_details;
    }
	
    @Override
	public void deleteNominee(Nominee_Details nominee_Details) {
		int NomineeId= nominee_Details.getNom_add_id();
		if(nomineeRepository.findById(NomineeId) != null) {
			nomineeRepository.deleteById(NomineeId);
		}else {
			//Handle Nominee NOT FOUND
		}
		
	}

}
