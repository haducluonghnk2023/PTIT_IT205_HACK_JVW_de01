package com.data.hackathon.service;

import com.data.hackathon.modal.entity.BusRoute;

import java.util.List;

public interface BusRouteService {
    List<BusRoute> getAllBuseRoutes();
    BusRoute getBusRouteById(Integer busRouteId);
    BusRoute createBusRoute(BusRoute busRoute);
    BusRoute updateBusRoute(Integer busRouteId, BusRoute busRoute);
    void deleteBusRoute(Integer busRouteId);
    List<BusRoute> findBusRouteByStartPointAndEndPoint(String startPoint, String endPoint);
}
