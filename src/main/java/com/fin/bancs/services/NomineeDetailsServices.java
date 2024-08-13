package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.bancs.customer.NomineeDetails;
import com.fin.bancs.dto.NomineeDto;
import com.fin.bancs.error.ResourceNotFoundException;
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
	public List<NomineeDetails> createModifyNomineeDetails(NomineeDto nomineeDetails,int flag){
		//Flag=1-> Create and flag=2 -> Modify
		NomineeDetails nominee_details = new NomineeDetails();
		List<NomineeDetails> nominee_detail_arr = new ArrayList<NomineeDetails>();
		nominee_details = NomineeMapper.mapToNominee(nomineeDetails, new NomineeDetails() );
		
		if(flag==2) {
			//check if customer exist then proceed else throw Error "Customer Not Exist"
			int NomineeId  = nominee_details.getNomineeId();
			if(nomineeRepository.findById(NomineeId) != null) {
				nominee_details= nomineeRepository.save(nominee_details);
				nominee_detail_arr.add(nominee_details);
				return nominee_detail_arr;
			}
		}
		BigInteger nomineeId = sequenceGenerator.generateSequence("NomineeId_seq");
		nominee_details.setNomineeId(nomineeId.intValue());
		//nomineeRepository.findBy(null, null);
		if(flag==1) {
			nominee_details = nomineeRepository.save(nominee_details);
			nominee_detail_arr.add(nominee_details);
		}
		return nominee_detail_arr;
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
    
	public List<NomineeDetails> createNomineesDetails(List<NomineeDto> nomineeDetails,int flag)
	{
		NomineeDetails nominee_details = new NomineeDetails();
		List<NomineeDetails> nominee_detail_out = new ArrayList<NomineeDetails>();
		for(NomineeDto nominee : nomineeDetails) {
			NomineeDetails nominee_details_temp = new NomineeDetails();
		nominee_details = NomineeMapper.mapToNominee(nominee, new NomineeDetails());
		BigInteger nomineeId = sequenceGenerator.generateSequence("NomineeId_seq");
		nominee_details.setNomineeId(nomineeId.intValue());
		nominee_details_temp=nomineeRepository.save(nominee_details);
		nominee_detail_out.add(nominee_details_temp);
		}
		return nominee_detail_out;	
	}

    
	public List<NomineeDetails> findNomineeByOwnerId(int ownerId) {
		List<NomineeDetails> nominee_detail_arr = new ArrayList<NomineeDetails>();
		nominee_detail_arr = nomineeRepository.findByOwnerId(ownerId);
		
		if(nominee_detail_arr.size()==0) {
			throw new ResourceNotFoundException("Nominee Not Found");
		}else {
			return nominee_detail_arr;
		}
		
	}

}
