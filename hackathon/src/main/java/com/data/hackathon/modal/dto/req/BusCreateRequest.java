package com.data.hackathon.modal.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BusCreateRequest {
    @NotBlank(message = "Tên xe buýt không được để trống")
    private String busName;
    
    @NotBlank(message = "Biển số xe không được để trống")
    private String registrationNumber;
    
    @NotNull(message = "Tổng số ghế không được để trống")
    @Positive(message = "Tổng số ghế phải lớn hơn 0")
    private Integer totalSeats;
    
    private MultipartFile image;
}
