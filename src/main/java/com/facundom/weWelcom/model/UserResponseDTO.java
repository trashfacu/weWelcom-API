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
public class UserResponseDTO {
    private Integer userId;
    @Email
    private String userEmail;
    @NotBlank
    @NotNull
    private String userFirstName;
    @NotBlank @NotNull
    private String userLastName;
}
