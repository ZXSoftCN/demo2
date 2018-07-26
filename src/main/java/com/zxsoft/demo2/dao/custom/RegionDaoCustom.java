package com.zxsoft.demo2.dao.custom;

import com.zxsoft.demo2.domain.Region;

import java.nio.file.Path;
import java.util.List;

public interface RegionDaoCustom {
    List<Region> customQuery();

    List<Region> customQueryCriteria();

}
