package com.service.management.system.repository.area;

import com.service.management.system.domain.area.Area;
import com.service.management.system.mapper.AreaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisAreaRepository implements AreaRepository {
    private final AreaMapper areaMapper;
    @Override
    public List<Area> list(Area area) {
        return areaMapper.list(area);
    }
}
