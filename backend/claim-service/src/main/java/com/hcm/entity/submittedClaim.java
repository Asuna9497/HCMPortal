package com.hcm.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class submittedClaim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long memberId;
	
	private String firstName;
	
	private String lastName;
	
	private String assignedPhysician;
	
	private long claimId;

	private float claimAmount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate submittedDate;

	public submittedClaim(Long memberId, String firstName, String lastName, String assignedPhysician, long claimId,
			float claimAmount, LocalDate submittedDate) {
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.assignedPhysician = assignedPhysician;
		this.claimId = claimId;
		this.claimAmount = claimAmount;
		this.submittedDate = submittedDate;
	}
	
	
	

	

}
