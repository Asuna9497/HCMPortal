package com.hcm.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@NotBlank(message = "username  is mandatory")
	private String username;

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

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@OneToOne
	private Physician physician;

	@OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Claim> claims = new HashSet<Claim>();
	
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
