package com.hcm.service;

import java.util.Set;

import com.hcm.entity.Claim;
import com.hcm.entity.Member;

public interface ClaimService {
	
	public Claim addClaim(Claim claim);
	
	public Set<Claim> getClaimsOfMember(Member member);

}
