package com.hcm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.entity.Physician;
import com.hcm.repository.PhysicianRepository;
import com.hcm.service.PhysicianService;

@Service
public class PhysicianServiceImpl implements PhysicianService{
	@Autowired
	PhysicianRepository physicianRepository;

	@Override
	public List<Physician> getAllPhysicians() {
		return physicianRepository.findAll();
	}

}
