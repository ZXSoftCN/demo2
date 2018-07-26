package com.zxsoft.demo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "demo2")
public class ApplicalitionProperties {

    @Autowired
    private AppCrossOriginProperties appCrossOriginProperties;

    @Value("${Druid.datasource.url}")
    private String url;
    @Value("${Druid.datasource.username}")
    private String datasourceusername;
    @Value("${Druid.datasource.password}")
    private String datasourcepassword;
    @Value("${Druid.datasource.driver-class-name}")
    private String driverclassname;
    @Value("${Druid.datasource.dbcp2.pool-prepared-statements}")
    private boolean poolpreparedstatements;
    @Value("${Druid.tomcat.initial-size}")
    private int initialsize;
    @Value("${Druid.tomcat.max-active}")
    private int maxactive;
    @Value("${Druid.tomcat.min-idle}")
    private int minidle;
    @Value("${Druid.tomcat.max-wait}")
    private int maxwait;
    @Value("${Druid.tomcat.validation-query}")
    private String validationquery;
    @Value("${Druid.tomcat.test-on-borrow}")
    private boolean testonborrow;
    @Value("${Druid.tomcat.test-while-idle}")
    private boolean testwhileidle;
    @Value("${uploadPath}")
    private String uploadPath;
    @Value("${RandomString}")
    private String randomString;
    @Value("${fastjson.dateFormat}")
    private String fastJsonDateFormat;
    @Value("${RandomLessHundred}")
    private int randomLessHundred;
    @Value("${demoDateFormat}")
    private String demoDateFormat;
    @Value("${demoShortDateFormat}")
    private String demoShortDateFormat;

//    private List<String> locations = new ArrayList<>();

    public void setPackagepath(String packagepath) {
        this.packagepath = packagepath;
    }

    private String packagepath;

    public String getPackagepath() {
        return packagepath;
    }

    public String getUrl() {
        return url;
    }

    public String getDatasourceusername() {
        return datasourceusername;
    }

    public String getDatasourcepassword() {
        return datasourcepassword;
    }

    public String getDriverclassname() {
        return driverclassname;
    }

    public boolean isPoolpreparedstatements() {
        return poolpreparedstatements;
    }

    public int getInitialsize() {
        return initialsize;
    }

    public int getMaxactive() {
        return maxactive;
    }

    public int getMinidle() {
        return minidle;
    }

    public int getMaxwait() {
        return maxwait;
    }

    public String getValidationquery() {
        return validationquery;
    }

    public boolean isTestonborrow() {
        return testonborrow;
    }

    public boolean isTestwhileidle() {
        return testwhileidle;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public String getRandomString() {
        return randomString;
    }

    public List<String> getCrossOriginLocations() {
        return appCrossOriginProperties.getLocations();
    }

    public String getFastJsonDateFormat() {
        return fastJsonDateFormat;
    }

    public void setFastJsonDateFormat(String fastJsonDateFormat) {
        this.fastJsonDateFormat = fastJsonDateFormat;
    }

    public int getRandomLessHundred() {
        return randomLessHundred;
    }

    public String getDemoDateFormat() {
        return demoDateFormat;
    }

    public String getDemoShortDateFormat() {
        return demoShortDateFormat;
    }


}
