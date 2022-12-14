package com.hcm.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import com.hcm.annotation.ValidPassword;

import lombok.Data;
 
@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
     
    private Set<String> role;
    
    @NotBlank
    @ValidPassword
    private String password;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
}
