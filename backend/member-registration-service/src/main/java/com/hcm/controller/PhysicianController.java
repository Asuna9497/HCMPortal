package com.hcm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hcm.entity.Physician;
import com.hcm.payload.response.MessageResponse;
import com.hcm.serviceImpl.PhysicianServiceImpl;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/hcm/physician")
public class PhysicianController extends BaseController {
	
	@Autowired
	PhysicianServiceImpl physicianServiceImpl;
	
	@GetMapping
	public ResponseEntity<?> getMembers() {
		List<Physician> list = physicianServiceImpl.getAllPhysicians();
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: No physician found!"));
		}
	}
	

}
