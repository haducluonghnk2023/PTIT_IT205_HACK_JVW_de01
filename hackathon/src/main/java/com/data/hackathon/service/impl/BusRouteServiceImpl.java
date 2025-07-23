package com.data.hackathon.service.impl;

import com.data.hackathon.modal.entity.BusRoute;
import com.data.hackathon.repository.BusRouteRepository;
import com.data.hackathon.service.BusRouteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {
    @Autowired
    private BusRouteRepository busRouteRepository;
    @Override
    public List<BusRoute> getAllBuseRoutes() {
        return busRouteRepository.findAll();
    }

    @Override
    public BusRoute getBusRouteById(Integer busRouteId) {
        return busRouteRepository.findById(busRouteId).orElse(null);
    }

    @Override
    public BusRoute createBusRoute(BusRoute busRoute) {
        busRoute.setStatus(true);
        return busRouteRepository.save(busRoute);
    }

    @Override
    public BusRoute updateBusRoute(Integer busRouteId, BusRoute busRoute) {
        BusRoute existingBusRoute = busRouteRepository.findById(busRouteId).orElse(null);
        if (existingBusRoute != null) {
            existingBusRoute.setStartPoint(busRoute.getStartPoint());
            existingBusRoute.setEndPoint(busRoute.getEndPoint());
            existingBusRoute.setTripInformation(busRoute.getTripInformation());
            existingBusRoute.setDriverName(busRoute.getDriverName());
            existingBusRoute.setStatus(busRoute.getStatus());
            return busRouteRepository.save(existingBusRoute);
        }
        return null;
    }

    @Override
    public void deleteBusRoute(Integer busRouteId) {
        BusRoute busRoute = busRouteRepository.findById(busRouteId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy chuyến đi với ID: " + busRouteId));
        busRoute.setStatus(false);
        busRouteRepository.save(busRoute);
    }

    @Override
    public List<BusRoute> findBusRouteByStartPointAndEndPoint(String startPoint, String endPoint) {
        return busRouteRepository.findBusRouteByStartPointAndEndPoint(startPoint, endPoint);
    }
}
