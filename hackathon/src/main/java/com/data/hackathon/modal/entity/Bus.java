package com.data.hackathon.modal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "bus")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;
    @Column(name = "bus_name", nullable = false, unique = true)
    private String busName;
    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;
    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;
    private String imageBus;
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<BusRoute> busRoutes;
}
