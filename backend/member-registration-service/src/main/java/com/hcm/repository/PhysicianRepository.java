package com.hcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcm.entity.Physician;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Integer> {
	
	@Query("select physician from Physician physician where physician.active = true")
	List<Physician> findActivePhysician();

}
