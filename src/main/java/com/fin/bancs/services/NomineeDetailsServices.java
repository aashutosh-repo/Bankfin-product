package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public List<NomineeDetails> createNomineesDetails(List<NomineeDto> nomineeDetails,int flag)
	{
		NomineeDetails nominee_details;
		List<NomineeDetails> nominee_detail_out = new ArrayList<NomineeDetails>();
		for(NomineeDto nominee : nomineeDetails) {
			NomineeDetails nominee_details_temp;
		nominee_details = NomineeMapper.mapToNominee(nominee, new NomineeDetails());
		BigInteger nomineeId = sequenceGenerator.generateSequence("NomineeId_seq");
		nominee_details.setNomineeId(nomineeId.intValue());
		nominee_details_temp=nomineeRepository.save(nominee_details);
		nominee_detail_out.add(nominee_details_temp);
		}
		return nominee_detail_out;	
	}

	@Override
	public List<NomineeDetails> modifyNomineeDetails(List<NomineeDto> nomineeDetails,int flag){
		NomineeDetails nominee_details;
		List<NomineeDetails> nominee_detail_arr = new ArrayList<>();
		if(!nomineeDetails.isEmpty()) {
			for(NomineeDto nominee : nomineeDetails) {
                new NomineeDetails();
                NomineeDetails nominee_details_temp;
				Optional<NomineeDetails> nomineeOut = nomineeRepository.findByNomineeRefNum(nominee.getNomineeRefNum());
				if(nomineeOut.isPresent()) {
					nominee_details = NomineeMapper.mapToNominee(nominee, new NomineeDetails());
					nominee_details.setNomineeId(nomineeOut.get().getNomineeId());
					nominee_details_temp=nomineeRepository.save(nominee_details);
					
					nominee_detail_arr.add(nominee_details_temp);

				}else {
					System.out.println("There is No Details Present");
				}
			}
		}
		return nominee_detail_arr;
    }
	
    @Override
	public void deleteNominee(NomineeDto nominee_Details) {
		int NomineeId= nominee_Details.getNomAddId();
		if(nomineeRepository.findById(NomineeId).isPresent()) {
			nomineeRepository.deleteById(NomineeId);
		}else {
			throw new ResourceNotFoundException("Nominee Not Existing with given Id"+ NomineeId);
		}
		
	}

    @Override
	public List<NomineeDetails> findNomineeByOwnerId(int ownerId) {
		List<NomineeDetails> nominee_detail_arr;
		nominee_detail_arr = nomineeRepository.findByOwnerId(ownerId);
		
		if(nominee_detail_arr.isEmpty()) {
			throw new ResourceNotFoundException("Nominee Not Found");
		}else {
			return nominee_detail_arr;
		}
		
	}

}
