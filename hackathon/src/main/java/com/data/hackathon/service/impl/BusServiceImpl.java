package com.data.hackathon.service.impl;

import com.data.hackathon.modal.dto.req.BusCreateRequest;
import com.data.hackathon.modal.entity.Bus;
import com.data.hackathon.repository.BusRepository;
import com.data.hackathon.repository.BusRouteRepository;
import com.data.hackathon.service.BusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusRouteRepository busRouteRepository;
    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public Bus getBusById(Integer busId) {
        return busRepository.findById(busId).orElse(null);
    }

    @Override
    public Bus createBus(Bus bus) {
        bus.setStatus(true);
        return busRepository.save(bus);
    }

    @Override
    public Bus updateBus(Integer busId, Bus bus) {
        Bus existingBus = busRepository.findById(busId).orElse(null);
        if (existingBus != null) {
            existingBus.setBusName(bus.getBusName());
            existingBus.setRegistrationNumber(bus.getRegistrationNumber());
            existingBus.setTotalSeats(bus.getTotalSeats());
            existingBus.setImageBus(bus.getImageBus());
            return busRepository.save(existingBus);
        }
        return null;
    }

    @Override
    public void deleteBus(Integer busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy xe buýt với ID: " + busId));

        boolean hasActiveRoutes = busRouteRepository.existsByBus_BusIdAndStatus(busId, true);

        if (hasActiveRoutes) {
            throw new IllegalStateException("Xe buýt này đang có chuyến đi hoạt động, không thể xóa");
        }

        bus.setStatus(false);
        busRepository.save(bus);
    }

}
