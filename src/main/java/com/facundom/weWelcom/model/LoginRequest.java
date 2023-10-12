package com.facundom.weWelcom.model;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    String userEmail;
    String userPassword;
}
