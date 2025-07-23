package com.data.hackathon.modal.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusResponse {
    private Integer busId;
    private String busName;
    private String registrationNumber;
    private Integer totalSeats;
    private MultipartFile image;
    private Boolean status;
}
