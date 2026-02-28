package com.ecommerce.user_service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    @NotBlank(message = "Required field!")
    private String firstName;

    @NotBlank(message = "Required field!")
    private String lastName;

    @Email(message = "Invalid Email!")
    @NotBlank(message = "Required field!")
    private String email;

    @Size(min = 6, message = "Password mus be at least 6 characters ")
    @NotBlank(message = "Required field!")
    private String password;

    @Pattern(regexp = "\\d{10}",message = "Phone number must be exactly 10 digits")
    @NotBlank(message = "Required field!")
    private String phoneNo;

    public UserRequestDto() {  }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
