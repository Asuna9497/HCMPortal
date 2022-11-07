package com.hcm.service;

import java.util.List;

import com.hcm.entity.Member;

public interface MemberService {
	
	public Member addMember(Member member);
		
	public List<Member> getMembers(Long memberId, String firstName, String LastName, String pysicianName, Long claimId );
	
	public List<Member> getAllMembers();
	
	public Member findMemberById(Long memberId);
	
	public Member findMemberByUsername(String username);
	
	

}
