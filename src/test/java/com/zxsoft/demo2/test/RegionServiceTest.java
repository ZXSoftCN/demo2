package com.zxsoft.demo2.test;

import com.zxsoft.demo2.domain.Region;
import com.zxsoft.demo2.service.BaseService;
import com.zxsoft.demo2.service.Impl.RegionServiceImpl;
import com.zxsoft.demo2.service.Impl.RegionServiceImpl2;
import com.zxsoft.demo2.service.RegionService;
import com.zxsoft.demo2.service.RegionService2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.Assert.*;

public class RegionServiceTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionService2 regionService2;


    @Test
    public void getPath(){
        logger.info("logger level:Info.Beggining--------------");
        String strPath = regionService.getPath().toAbsolutePath().toString();
        String strBasePath = regionService.getOriginPath().toAbsolutePath().toString();
        String strPath2 = regionService2.getPath().toAbsolutePath().toString();
        System.out.println(String.format("BaseService rootPath:%s",strBasePath));
        System.out.println(String.format("RegionService rootPath:%s",strPath));
        System.out.println(String.format("RegionService2 rootPath:%s",strPath2));
    }

    @Test
    public void addResource() {

    }

    @Test
    public void modifyResource() {
    }

    @Test
    public void modifyResourceFile() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getById() {
        Optional<Region> item = regionService.getById("402883e664b6d10e0164b6d13b5c0000");
        if (item.isPresent()) {
            System.out.println("OK");
            System.out.println(item.get().getName());
        }

    }
}