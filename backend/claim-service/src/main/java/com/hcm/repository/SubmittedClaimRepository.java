package com.hcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcm.entity.submittedClaim;

@Repository
public interface SubmittedClaimRepository extends JpaRepository<submittedClaim, Long> {

}
