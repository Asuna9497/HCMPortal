package com.hcm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.entity.submittedClaim;
import com.hcm.repository.SubmittedClaimRepository;
import com.hcm.service.submittedClaimservice;

@Service
public class SubmittedClaimServiceImpl implements submittedClaimservice {
	
	@Autowired
	SubmittedClaimRepository submittedRepository;

	@Override
	public void saveSubmittedClaims(List<submittedClaim> claimList) {
		submittedRepository.saveAll(claimList);
	}

}
