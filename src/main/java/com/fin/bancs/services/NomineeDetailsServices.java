package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.bancs.customer.NomineeDetails;
import com.fin.bancs.dto.NomineeDto;
import com.fin.bancs.mapper.NomineeMapper;
import com.fin.bancs.repository.Nominee_Repository;
import com.fin.bancs.services.si.Nominee_Service_Interface;
import com.fin.bancs.utils.SequenceGenerator;

@Service
public class NomineeDetailsServices implements Nominee_Service_Interface{

    @Autowired
    private Nominee_Repository nomineeRepository;
	@Autowired
	private SequenceGenerator sequenceGenerator;


    @Override
	public NomineeDetails createModifyNomineeDetails(NomineeDto nomineeDetails,int flag){
		//Flag=1-> Create and flag=2 -> Modify
		NomineeDetails nominee_details = new NomineeDetails();
		nominee_details = NomineeMapper.mapToNominee(nomineeDetails, new NomineeDetails() );
		
		if(flag==2) {
			//check if customer exist then proceed else throw Error "Customer Not Exist"
			int NomineeId  = nominee_details.getNomineeId();
			if(nomineeRepository.findById(NomineeId) != null) {
				return nomineeRepository.save(nominee_details);
			}
		}
		BigInteger nomineeId = sequenceGenerator.generateSequence("NomineeId_seq");
		nominee_details.setNomineeId(nomineeId.intValue());
		int nomineee_id=1;
		//nomineeRepository.findBy(null, null);
		if(flag==1) {
			nominee_details = nomineeRepository.save(nominee_details);
		}
        Optional<NomineeDetails> nominee = nomineeRepository.findById(nomineee_id);
        if(!nominee.isEmpty()){
		}
		return nominee_details;
    }
	
    @Override
	public void deleteNominee(NomineeDto nominee_Details) {
		int NomineeId= nominee_Details.getNomAddId();
		if(nomineeRepository.findById(NomineeId) != null) {
			nomineeRepository.deleteById(NomineeId);
		}else {
			//Handle Nominee NOT FOUND
		}
		
	}

}
