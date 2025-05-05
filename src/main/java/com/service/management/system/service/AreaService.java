package com.service.management.system.service;

import com.service.management.system.domain.area.Area;
import com.service.management.system.dto.area.AreaReadDto;
import com.service.management.system.repository.area.AreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AreaService {
    private final AreaRepository areaRepository;
    public List<Area> getAreasByType(int areaType) {
        Area area = Area.builder()
                .areaType(areaType)
                .build();
        return areaRepository.list(area);
    }
}
