package com.service.management.system.repository.area;

import com.service.management.system.domain.area.Area;

import java.util.List;

public interface AreaRepository {
    List<Area> list(Area area);
}
