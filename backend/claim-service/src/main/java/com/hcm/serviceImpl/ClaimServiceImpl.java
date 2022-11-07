package com.hcm.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.entity.Claim;
import com.hcm.entity.Member;
import com.hcm.repository.ClaimRepository;
import com.hcm.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService{
	
	@Autowired
	ClaimRepository claimRepository;

	@Override
	public Claim addClaim(Claim claim) {
		return claimRepository.save(claim);
	}

	@Override
	public Set<Claim> getClaimsOfMember(Member member) {
		return claimRepository.findByMember(member);
	}

}
