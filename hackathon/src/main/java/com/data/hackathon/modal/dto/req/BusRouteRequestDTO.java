package com.data.hackathon.modal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusRouteRequestDTO {
    private String startPoint;
    private String endPoint;
    private String tripInformation;
    private String driverName;
    private Boolean status;
    private Integer busId;
}
