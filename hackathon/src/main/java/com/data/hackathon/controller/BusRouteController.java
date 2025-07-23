package com.data.hackathon.controller;

import com.data.hackathon.modal.dto.res.ApiResponse;
import com.data.hackathon.modal.entity.BusRoute;
import com.data.hackathon.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bus-routes")
public class BusRouteController {
    @Autowired
    private BusRouteService busRouteService;

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllBusRoutes() {
        List<BusRoute> busRoutes = busRouteService.getAllBuseRoutes().stream()
                .sorted(Comparator.comparing(BusRoute::getStartPoint))
                .collect(Collectors.toList());

        List<Map<String, Object>> routeList = busRoutes.stream().map(route -> {
            Map<String, Object> routeMap = new HashMap<>();
            routeMap.put("routeId", route.getBusRouteId());
            routeMap.put("driverName", route.getDriverName());
            routeMap.put("startingPoint", route.getStartPoint());
            routeMap.put("endPoint", route.getEndPoint());
            routeMap.put("tripInformation", route.getTripInformation());

            // Add status information
            boolean isActive = route.getStatus() != null ? route.getStatus() : true;
            routeMap.put("status", isActive ? 1 : 0);
            routeMap.put("statusText", isActive ? "Đang hoạt động" : "Đã dừng");

            return routeMap;
        }).collect(Collectors.toList());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("routes", routeList);
        responseData.put("totalRoutes", routeList.size());

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                true,
                "Lấy danh sách chuyến đi thành công",
                responseData,
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BusRoute>> createBusRoute(@RequestBody BusRoute busRoute) {
        BusRoute createdBusRoute = busRouteService.createBusRoute(busRoute);
        ApiResponse<BusRoute> response = new ApiResponse<>(
                true,
                "Thêm chuyến đi thành công",
                createdBusRoute,
                HttpStatus.CREATED
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{busRouteId}")
    public ResponseEntity<ApiResponse<BusRoute>> updateBusRoute(@PathVariable Integer busRouteId,@RequestBody BusRoute busRoute) {
        BusRoute updatedBusRoute = busRouteService.updateBusRoute(busRouteId, busRoute);
        ApiResponse<BusRoute> response = new ApiResponse<>(true,"Update bus route successfully", updatedBusRoute, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{busRouteId}")
    public ResponseEntity<ApiResponse<String>> deleteBusRoute(@PathVariable Integer busRouteId) {
        try {
            busRouteService.deleteBusRoute(busRouteId);
            return ResponseEntity.ok(
                new ApiResponse<>(true, "Delete bus route successfully", null, HttpStatus.OK)
            );
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse<>(false, e.getMessage(), null, HttpStatus.BAD_REQUEST)
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                new ApiResponse<>(false, "An error occurred while deleting bus route: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR)
            );
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Map<String, Object>>> searchBusRoutes(@RequestParam String startPoint, @RequestParam String endPoint) {
        List<BusRoute> busRoutes = busRouteService.findBusRouteByStartPointAndEndPoint(startPoint, endPoint);
        List<Map<String, Object>> routeList = busRoutes.stream().map(route -> {
            Map<String, Object> routeMap = new HashMap<>();
            routeMap.put("routeId", route.getBusRouteId());
            routeMap.put("driverName", route.getDriverName());
            routeMap.put("startingPoint", route.getStartPoint());
            routeMap.put("endPoint", route.getEndPoint());
            routeMap.put("tripInformation", route.getTripInformation());

            // Add status information
            boolean isActive = route.getStatus() != null ? route.getStatus() : true;
            routeMap.put("status", isActive ? 1 : 0);
            routeMap.put("statusText", isActive ? "Đang hoạt động" : "Đã dừng");

            return routeMap;
        }).collect(Collectors.toList());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("routes", routeList);
        responseData.put("totalRoutes", routeList.size());

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                true,
                "Tìm kiếm chuyến đi thành công",
                responseData,
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
