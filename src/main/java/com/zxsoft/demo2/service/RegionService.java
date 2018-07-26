package com.zxsoft.demo2.service;

import com.zxsoft.demo2.domain.Region;
import com.zxsoft.demo2.domain.RegionResource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface RegionService extends BaseService<Region> {

    void initResourceDirectory();
    RegionResource addResource(MultipartFile file,String regionId);
    RegionResource addResource(byte[] bytes,String originFileName,String postfix,String regionId);
    void addResource(Region region,MultipartFile file);
    RegionResource modifyResource(RegionResource regionResource);
    RegionResource modifyResource(RegionResource regionResource,MultipartFile file);
    RegionResource modifyResource(RegionResource regionResource,byte[] bytes,String originFileName,String postfix);
    void delete(Region region);
    void deleteResource(RegionResource regionResource);
}
