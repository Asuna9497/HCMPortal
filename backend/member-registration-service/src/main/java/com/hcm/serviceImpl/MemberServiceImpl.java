package com.hcm.serviceImpl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.entity.Claim;
import com.hcm.entity.Member;
import com.hcm.entity.Physician;
import com.hcm.repository.MemberRepository;
import com.hcm.repository.PhysicianRepository;
import com.hcm.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	PhysicianRepository physicianRepository;

	@Override
	public Member addMember(Member member) {
		List<Physician> physicians = physicianRepository.findActivePhysician();
		Random index = new Random();
		Physician physician = physicians.get(index.nextInt(physicians.size()));
		member.setPhysician(physician);
		Member savedMember = memberRepository.save(member);
		if (null != savedMember) {
			physician.setActive(false);
			physicianRepository.save(physician);
		}
		
		return savedMember;
	}

	@Override
	public List<Member> getMembers(Long memberId, String firstName, String LastName, String pysicianName,
			Long claimId) {
		List<Member> members = memberRepository.findAll();
		List<Member> result = members.stream()
				.filter(m -> m.getId() == memberId
						|| (m.getFirstName().equalsIgnoreCase(firstName) && m.getLastName().equalsIgnoreCase(LastName))
						|| m.getPhysician().getName().equalsIgnoreCase(pysicianName) || isClaimPresent(m, claimId))
				.collect(Collectors.toList());
		return result;
	}

	private boolean isClaimPresent(Member m, Long claimId) {
		List<Claim> claims = m.getClaims().stream().filter(e -> e.getId() == claimId).collect(Collectors.toList());
		return !claims.isEmpty();
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Member findMemberById(Long memberId) {
		return memberRepository.findById(memberId).get();
	}
	
	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}

	public Member findMemberByUsername(String username) {
		return memberRepository.findByUsername(username);
	}

}
