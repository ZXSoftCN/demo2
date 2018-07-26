package com.zxsoft.demo2.service;

import com.zxsoft.demo2.domain.Region;
import com.zxsoft.demo2.domain.RegionResource;
import org.springframework.web.multipart.MultipartFile;

public interface RegionService2 extends BaseService<Region> {

    void initResourceDirectory();
    void addResource(MultipartFile file);
    void modifyResource(RegionResource regionResource);
    void modifyResourceFile(RegionResource regionResource, MultipartFile file);
    void delete(Region region);
}
