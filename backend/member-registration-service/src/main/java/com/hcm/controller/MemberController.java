package com.hcm.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcm.entity.Claim;
import com.hcm.entity.Member;
import com.hcm.payload.response.MessageResponse;
import com.hcm.serviceImpl.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/hcm/member")
public class MemberController extends BaseController {

	@Autowired
	MemberServiceImpl memberServiceImpl;
	

	@Autowired
	RestTemplate restTemplate;

	@PostMapping
	public ResponseEntity<?> addMember(@Valid @RequestBody Member member) {
		Member mem = memberServiceImpl.addMember(member);
		if (null != mem) {
			return new ResponseEntity<>(mem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping
	public ResponseEntity<?> getMembers() {
		List<Member> list = memberServiceImpl.getAllMembers();
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: No member found!"));
		}
	}

	
	@GetMapping("/search")
	public ResponseEntity<?> findMember(@RequestParam(value = "memberId", required = false) Long memberId,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "physicianName", required = false) String physicianName,
			@RequestParam(value = "claimId", required = false) Long claimId) {
		
		log.debug("inside search");

		if (null == firstName && null == lastName && null == memberId && null == physicianName && null == claimId) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: At least 1 input is required !"));
		} else if ((null != firstName && null == lastName) || (null == firstName && null != lastName)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Both first and last name is required!"));
		} else {
			List<Member> list = memberServiceImpl.getMembers(memberId, firstName, lastName, physicianName, claimId);
			
			if (!list.isEmpty()) {
				return new ResponseEntity<>(list, HttpStatus.OK);	
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: No member found!"));
			}
		}
	}

	@PostMapping("/claim/{memberId}")
	public ResponseEntity<?> addClaim(@PathVariable("memberId") Long memberId, @Valid @RequestBody Claim claim) {
		Member member = memberServiceImpl.findMemberById(memberId);
		if (null != member) {
			log.debug("inside add claim");
			claim.setMember(member);
			String url = "http://localhost:8082/api/v1/hcm/claim/member/" + memberId + "/type/" + claim.getType() + "/amount/"
					+ claim.getAmount() + "/remarks/" + claim.getRemarks();

			Claim submittedClaim = this.restTemplate.getForObject(url, Claim.class);
			if (null != submittedClaim) {
				return ResponseEntity.ok(new MessageResponse("Claim submitted successfully!"));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Unable to submit claim !"));
			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: No member found!"));
		}
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getMemberByUsername(@PathVariable("username") String username) {
		Member member = memberServiceImpl.findMemberByUsername(username);
		if (null != member) {
			return new ResponseEntity<>(member, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: No member found!"));
		}
	}

}
