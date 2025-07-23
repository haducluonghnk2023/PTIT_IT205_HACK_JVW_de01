package com.data.hackathon.controller;

import com.data.hackathon.modal.dto.req.BusCreateRequest;
import com.data.hackathon.modal.dto.res.ApiResponse;
import com.data.hackathon.modal.entity.Bus;
import com.data.hackathon.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();

        List<Map<String, Object>> busList = buses.stream().map(bus -> {
            Map<String, Object> busMap = new HashMap<>();
            busMap.put("busId", bus.getBusId());
            busMap.put("busName", bus.getBusName());
            busMap.put("registrationNumber", bus.getRegistrationNumber());
            busMap.put("totalSeats", bus.getTotalSeats());
            busMap.put("image", bus.getImageBus());
            boolean isActive = bus.getStatus() != null ? bus.getStatus() : true;
            busMap.put("status", isActive ? 1 : 0);
            busMap.put("statusText", isActive ? "Hoạt động" : "Không hoạt động");
            return busMap;
        }).collect(Collectors.toList());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("buses", busList);

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                true,
                "Lấy danh sách xe buýt thành cong",
                responseData,
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<ApiResponse<Bus>> createBus(@RequestBody Bus bus) {
        Bus createdBus = busService.createBus(bus);
        ApiResponse<Bus> response = new ApiResponse<>(true, "Add bus successfully", createdBus, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

    @PutMapping("/{busId}")
    public ResponseEntity<ApiResponse<Bus>> updateBus(@PathVariable Integer busId,@RequestBody Bus bus) {
        if (bus.getBusName() == null || bus.getBusName().trim().isEmpty()) {
            ApiResponse<Bus> response = new ApiResponse<>(false, "Bus name cannot be empty", null, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Bus updatedBus = busService.updateBus(busId, bus);
        ApiResponse<Bus> response = new ApiResponse<>(true,"Update bus successfully", updatedBus, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{busId}")
    public ResponseEntity<ApiResponse<String>> deleteBus(@PathVariable Integer busId) {
        try {
            busService.deleteBus(busId);
            return ResponseEntity.ok(
                new ApiResponse<>(true, "Delete bus successfully", null, HttpStatus.OK)
            );
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>(false, e.getMessage(), null, HttpStatus.BAD_REQUEST)
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                new ApiResponse<>(false, "The error occurred while deleting bus: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR)
            );
        }
    }
}
