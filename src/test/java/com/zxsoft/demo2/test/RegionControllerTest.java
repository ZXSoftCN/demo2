package com.zxsoft.demo2.test;

import com.zxsoft.demo2.config.AppCrossOriginProperties;
import com.zxsoft.demo2.config.ApplicalitionProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CrossOrigin(origins = "http://localhost:8080")
public class RegionControllerTest extends BaseTest {


    @Qualifier("applicalitionProperties")
    @Autowired
    private ApplicalitionProperties applicalitionProperties;
    @Autowired
    private AppCrossOriginProperties appCrossOriginProperties;


    @Test
    @Rollback(value = true)
    public void addRegion() {
        //测试属性组
        List<String> lstCrossOrigin = appCrossOriginProperties.getLocations();
        List<String> lstOrgCC = applicalitionProperties.getCrossOriginLocations();

        String requestBody = "{\"code\":\"006\",\"name\":\"郑州\"}";
        RequestBuilder request;
        request = post("/api/region/add")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    @Test
//    @Rollback(value = true)
    public void addRegionResource() throws IOException {
        //测试属性组
        String fileLocal="E:\\添加微信号.docx";
        Path uploadFile = Paths.get(fileLocal);
//        Files.createTempFile(String.format("%s-1",uploadFile.getFileName()),null,null);
        FileSystemResource resource = new FileSystemResource(new File(fileLocal));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);

//        HttpEntity<MultiValueMap<String, Object>> httpEntity httpEntity= new HttpEntity<MultiValueMap<String, Object>>(param);
//        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, httpEntity, String.class);
//        System.out.println(responseEntity.getBody());

        String requestBody = "{\"code\":\"006\",\"name\":\"郑州\"}";
        RequestBuilder request;

        MultiValueMap<String,String> multiParams = new LinkedMultiValueMap<>();
        multiParams.add("regionId","402881e564c015100164c0154cbe0000");
        multiParams.add("postfix","docx");//附件后缀

        request = post("/api/regionresource/addByBytes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(Files.readAllBytes(uploadFile))
                .params(multiParams);
//                .requestAttr("file",resource )

        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    @Test
    @Rollback(value = true)
    public void modifyRegionResource() throws IOException {
        //测试属性组
        String fileLocal="E:\\添加微信号.docx";
        Path uploadFile = Paths.get(fileLocal);
//        Files.createTempFile(String.format("%s-1",uploadFile.getFileName()),null,null);
        FileSystemResource resource = new FileSystemResource(new File(fileLocal));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);

        RequestBuilder request;

        MultiValueMap<String,String> multiParams = new LinkedMultiValueMap<>();
        multiParams.add("regionResourceId","402883e464d045c90164d04601410000");
        multiParams.add("postfix","docx");//附件后缀

        request = post("/api/regionresource/modifyByBytes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(Files.readAllBytes(uploadFile))
                .params(multiParams);
//                .requestAttr("file",resource )

        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void queryRegion() {
        RequestBuilder request;
        String regionId = "402881e564c015100164c0154cbe0000";// "402881e564c015100164c0154cbe0000";
        request = get(String.format("/api/region/get/%s",regionId))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.TEXT_PLAIN);

        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    @Test
    public void queryRegionByPage() {
        RequestBuilder request;
        String regionId = "402881e564c015100164c0154cbe0000";
        MultiValueMap<String,String> mapValue = new LinkedMultiValueMap<>();
        mapValue.add("page","0");
        mapValue.add("size","3");
        mapValue.add("sort","code");
        request = get("/api/region/get")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.TEXT_PLAIN)
                .params(mapValue);
        try {
            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }
}