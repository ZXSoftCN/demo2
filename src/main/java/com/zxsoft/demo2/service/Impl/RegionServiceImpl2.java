package com.zxsoft.demo2.service.Impl;

import com.zxsoft.demo2.dao.RegionDao;
import com.zxsoft.demo2.domain.Region;
import com.zxsoft.demo2.domain.RegionResource;
import com.zxsoft.demo2.service.RegionService;
import com.zxsoft.demo2.service.RegionService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class RegionServiceImpl2 extends BaseServiceImpl<Region> implements RegionService2 {

    private final String regionResPath = "region2";

    @Autowired
    private RegionDao regionDao;

//    public RegionServiceImpl(){
//    }

    @Override
    protected void initPath() {
        super.initPath();
        rootUploadPath = super.getPath().resolve(regionResPath);
    }

    @Override
    public Path getPath() {
        if (rootUploadPath == null) {
            initPath();
        }
        return rootUploadPath;
    }

    @Override
    public JpaRepository<Region, String> getBaseDao() {
        return regionDao;
    }

    @Override
    public void initResourceDirectory() {

    }

    @Override
    public void modifyResource(RegionResource regionResource) {

    }

    @Override
    public void modifyResourceFile(RegionResource regionResource, MultipartFile file) {

    }

    @Override
    public void delete(Region region) {

    }

    @Override
    public void addResource(MultipartFile file) {

    }
}
