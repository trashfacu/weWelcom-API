package com.facundom.weWelcom.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    // Auth Response
    private Integer userId;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
}
