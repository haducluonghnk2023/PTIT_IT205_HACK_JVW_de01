package com.data.hackathon.modal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "bus_route")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busRouteId;

    @Column(name = "start_point", nullable = false)
    private String startPoint;

    @Column(name = "end_point", nullable = false)
    private String endPoint;

    @Column(name = "trip_information", nullable = false)
    private String tripInformation;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    @JsonManagedReference
    private Bus bus;
}