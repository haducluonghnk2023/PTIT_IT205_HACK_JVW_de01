package com.data.hackathon.modal.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private HttpStatus status;
}
