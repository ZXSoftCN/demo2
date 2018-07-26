package com.zxsoft.demo2.controller.convert;

import com.zxsoft.demo2.dao.RegionDao;
import com.zxsoft.demo2.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToRegion implements Converter<String,Region> {

    @Autowired
    private RegionDao regionDao;

    @Override
    public Region convert(String s) {
        Optional<Region> regItem = regionDao.findById(s);
        Optional<Region> regItemByCode = regionDao.findRegionByCode(s);
        if (regItem.isPresent()) {
            return regItem.get();
        }
        return regItem.orElse(regItemByCode.orElse(null));
    }
}
