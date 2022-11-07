package com.hcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcm.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository <Claim, Long> {

}
