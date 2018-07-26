package com.zxsoft.demo2.dao.vo;

import com.zxsoft.demo2.domain.Region;

public interface RegionCount {
    Region getRegion();
    Integer getBankCount();

    //处理结果为null的方式
    default int getBankCountNotNull() {
        return getBankCount() == null ? 0 : getBankCount();
    }
}
