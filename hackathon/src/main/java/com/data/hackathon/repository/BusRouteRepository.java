package com.data.hackathon.repository;

import com.data.hackathon.modal.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
    boolean existsByBus_BusId(Integer busId);
    List<BusRoute> findBusRouteByStartPointAndEndPoint(String startPoint, String endPoint);
    boolean existsByBus_BusIdAndStatus(Integer busId, Boolean status);
}
