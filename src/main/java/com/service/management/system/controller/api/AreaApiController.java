package com.service.management.system.controller.api;

import com.service.management.system.domain.area.Area;
import com.service.management.system.dto.ApiResponse;
import com.service.management.system.service.AreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/areas")
public class AreaApiController {
    private final AreaService areaService;
    @GetMapping("/{areaType}")
    public ResponseEntity<?> getAreasByType(@PathVariable("areaType") int areaType) {
        List<Area> areas = areaService.getAreasByType(areaType);
        if (areas.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.failure("No areas found for areaType: " + areaType));
        }
        return ResponseEntity.ok(ApiResponse.success(areas));
    }
}
