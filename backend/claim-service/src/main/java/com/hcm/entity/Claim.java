package com.hcm.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Claim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ClaimType type;
	
	@Min(value = 1, message = "amount cannot be less than 1")
	private float amount;
	
	@NotBlank(message = "Remarks are mandatory")
	@Length(max = 1000, message = "Remarks must be at most 1000 characters")
	private String remarks;
	
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate claimDate;
	
	@OneToOne
	private Member member;
	
	private boolean select;

	@Override
	public String toString() {
		return "Claim [id=" + id + ", type=" + type + ", amount=" + amount + ", remarks=" + remarks + ", claimDate="
				+ claimDate + ", member=" + member + ", select=" + select + "]";
	}
	

}
