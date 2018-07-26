package com.zxsoft.demo2.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/*
    通过实现TransactionManagementConfigurer接口可设置默认事务管理器，
    但必须是容器中有多个transactionManager，否则无效。
*/
@Configuration
@EnableTransactionManagement
public class DataSourceConfig  implements TransactionManagementConfigurer {

    @Qualifier("demo2-com.zxsoft.demo2.config.ApplicalitionProperties")
    @Autowired
    private ApplicalitionProperties applicalitionProperties;

    @Bean
    public DataSource dataSource() {

//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return builder.setType(EmbeddedDatabaseType.HSQL).build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(applicalitionProperties.getUrl());
        dataSource.setUsername(applicalitionProperties.getDatasourceusername());//用户名
        dataSource.setPassword(applicalitionProperties.getDatasourcepassword());//密码
        dataSource.setInitialSize(applicalitionProperties.getInitialsize());
        dataSource.setMaxActive(applicalitionProperties.getMaxactive());
        dataSource.setMinIdle(applicalitionProperties.getMinidle());
        dataSource.setMaxWait(applicalitionProperties.getMaxwait());
        dataSource.setValidationQuery(applicalitionProperties.getValidationquery());
        dataSource.setTestOnBorrow(applicalitionProperties.isTestonborrow());
        dataSource.setTestWhileIdle(applicalitionProperties.isTestwhileidle());
        dataSource.setPoolPreparedStatements(applicalitionProperties.isPoolpreparedstatements());
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(String.format("%s.domain", applicalitionProperties.getPackagepath()));
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean//(name = "JpaTransactionManager")
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    //保留。后续加入多个事务管理器后生效
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
