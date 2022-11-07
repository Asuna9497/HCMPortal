package com.hcm.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcm.entity.Claim;
import com.hcm.entity.Member;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	Set<Claim> findByMember(Member member);

}
