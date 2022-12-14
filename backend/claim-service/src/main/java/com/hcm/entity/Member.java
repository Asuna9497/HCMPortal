package com.hcm.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "firstName  is mandatory")
	@Size(min = 5, max = 20)
	private String firstName;

	@NotBlank(message = "lastName  is mandatory")
	@Size(min = 5, max = 20)
	private String lastName;

	@NotBlank(message = "email is mandatory")
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank(message = "address is mandatory")
	private String address;

	@NotBlank(message = "state is mandatory")
	private String state;

	@NotBlank(message = "city is mandatory")
	private String city;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dob;

	@OneToOne
	private Physician physician;

	@OneToMany
	private List<Claim> claims;
	
	public Member() {}
	
	public Member(String firstName, String lastName, String email, String address, String state, String city,
			LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.state = state;
		this.city = city;
		this.dob = dob;
	}

}
