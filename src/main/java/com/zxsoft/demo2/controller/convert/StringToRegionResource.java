package com.zxsoft.demo2.controller.convert;

import com.zxsoft.demo2.dao.RegionDao;
import com.zxsoft.demo2.dao.RegionRescourceDao;
import com.zxsoft.demo2.domain.Region;
import com.zxsoft.demo2.domain.RegionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToRegionResource implements Converter<String,Optional<RegionResource>> {


    @Autowired
    private RegionRescourceDao regionRescourceDao;

    @Override
    public Optional<RegionResource> convert(String s) {
        Optional<RegionResource> regItem = regionRescourceDao.findById(s);

        return regItem;
    }
}
