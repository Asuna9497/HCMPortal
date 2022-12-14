package com.hcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcm.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUsername(String username);

}
