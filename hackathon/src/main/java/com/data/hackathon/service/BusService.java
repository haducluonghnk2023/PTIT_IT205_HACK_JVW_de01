package com.data.hackathon.service;

import com.data.hackathon.modal.dto.req.BusCreateRequest;
import com.data.hackathon.modal.entity.Bus;

import java.util.List;

public interface BusService {
    List<Bus> getAllBuses();
    Bus getBusById(Integer busId);
    Bus createBus(Bus busCreateRequest);
    Bus updateBus(Integer busId, Bus bus);
    void deleteBus(Integer busId);
}
