package com.bridgelabz.bookstore.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
	
	@NotEmpty(message="Full name cant be empty")
    private  String fullName;

    @NotEmpty(message="Email cant be empty")
    private String email;

    @NotEmpty(message="PhoneNumber cant be empty")
    private String phoneNumber;

    @NotBlank(message="Password cant be blank")
    private String password;
}
