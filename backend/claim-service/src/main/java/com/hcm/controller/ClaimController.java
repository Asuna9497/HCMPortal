package com.hcm.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.entity.Claim;
import com.hcm.entity.ClaimType;
import com.hcm.entity.Member;
import com.hcm.entity.submittedClaim;
import com.hcm.payload.response.MessageResponse;
import com.hcm.serviceImpl.ClaimServiceImpl;
import com.hcm.serviceImpl.SubmittedClaimServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/hcm/claim")
public class ClaimController {

	@Autowired
	ClaimServiceImpl claimServiceImpl;
	
	@Autowired
	SubmittedClaimServiceImpl submittedClaimServiceImpl;

	@RequestMapping("/member/{memberId}/type/{type}/amount/{amount}/remarks/{remarks}")
	public Claim submitClaim(@PathVariable("memberId") Long memberId, @PathVariable("type") String type,
			@PathVariable("amount") float amount, @PathVariable("remarks") String remarks) {
		Claim claim = new Claim();
		Member member = new Member();
		member.setId(memberId);
		claim.setMember(member);
		switch (type) {
		case "Vision":
			claim.setType(ClaimType.Vision);
			break;
		case "Dental":
			claim.setType(ClaimType.Dental);
			break;
		case "Medical":
			claim.setType(ClaimType.Medical);
			break;
		default:
			break;
		}

		claim.setAmount(amount);
		claim.setRemarks(remarks);
		LocalDate claimDate = LocalDate.now();
		claim.setClaimDate(claimDate);
		Claim submittedClaim = claimServiceImpl.addClaim(claim);
		if (null != submittedClaim) {
			return submittedClaim;
		} else {
			return null;
		}

	}

	@RequestMapping("/member/{memberId}")
	public ResponseEntity<?> getClaimsOfMember(@PathVariable("memberId") Long memberId) {
		log.debug("inside getClaimsOfMember");
		Member member = new Member();
		member.setId(memberId);
		Set<Claim> claimsOfmember = claimServiceImpl.getClaimsOfMember(member);
		if(!claimsOfmember.isEmpty()) {
			 return new ResponseEntity<>(claimsOfmember, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/submittedClaims")
	public ResponseEntity<?> saveSubmittedClaims(@Valid @RequestBody List<Claim> req) {
		log.debug("inside saveSubmittedClaims");
		List<submittedClaim> submittedClaims = new ArrayList<submittedClaim>();
		for(Claim c : req) {
			if(c.isSelect()) {
				submittedClaim sc = new submittedClaim(c.getMember().getId(), c.getMember().getFirstName(), c.getMember().getLastName()
						, c.getMember().getPhysician().getName(),c.getId(), c.getAmount(), c.getClaimDate());
				submittedClaims.add(sc);
			}	
		}
		submittedClaimServiceImpl.saveSubmittedClaims(submittedClaims);
		return ResponseEntity.ok(new MessageResponse("claims submitted successfully!"))	;

	}
	


}
