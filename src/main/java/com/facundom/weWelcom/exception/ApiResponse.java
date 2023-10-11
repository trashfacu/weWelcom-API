package com.facundom.weWelcom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ApiResponse {
    private String message;
}
