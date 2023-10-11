package com.facundom.weWelcom.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    @Email
    private String userEmail;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
}
