package com.zxsoft.demo2.test;


import com.sun.imageio.plugins.common.ImageUtil;
import com.zxsoft.demo2.dao.BankDao;
import com.zxsoft.demo2.dao.RegionDao;
import com.zxsoft.demo2.dao.RegionRescourceDao;
import com.zxsoft.demo2.dao.vo.RegionCount;
import com.zxsoft.demo2.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.Console;
import java.util.*;

@Transactional
@Rollback(value = true)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainEntityTest {

    @Autowired
    private WebApplicationContext webContext;


    private MockMvc mockMvc;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private JpaRepository<TestBank,Integer> testBankDao;



    @Autowired
    private JpaRepository<ScOrgInfo,Integer> orgDao;
    @Autowired
    private RegionDao regionDao;
    @Autowired
    private RegionRescourceDao regionRescourceDao;


    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    @Rollback(value = true)
    public void createDefaultRegion(){
        Region region = new Region();
        region.setCode("002");
        region.setName("广州");
        region.setLogoUrl("/resources/img/region/001.icon");

        regionDao.save(region);
    }

    @Test
    @Rollback(value = false)
    public void createCascadeRegion() {
        Region reg = new Region();
        reg.setCode("003");
        reg.setName("深圳");
        regionDao.save(reg);

        RegionResource resItem1 = new RegionResource();
        resItem1.setRegion(reg);
        resItem1.setType("icon");
        resItem1.setResUrl("/icon/1.icon");

        RegionResource resItem2 = new RegionResource();
        resItem2.setRegion(reg);
        resItem2.setType("icon");
        resItem2.setResUrl("/icon/2.icon");

        HashSet<RegionResource> coll = new HashSet<>();
        coll.add(resItem1);
        coll.add(resItem2);

        regionRescourceDao.saveAll(coll);
    }

    @Test
    @Rollback(value = false)
    public void deleteCascadeRegion(){
        Optional<Region> reg = regionDao.findRegionByCode("003");
        if (reg.isPresent()) {
            regionDao.delete(reg.get());
        }
    }

    @Test
    @Rollback(value = false)
    public void updateCascadeRegion(){
        Optional<Region> reg = regionDao.findRegionByCode("003");
        if (reg.isPresent()) {
            Set<RegionResource> coll = reg.get().getResources();
            RegionResource[] arrRes = new RegionResource[]{};
            coll.toArray(arrRes);
            List<RegionResource> lst = new ArrayList<>(coll);
            RegionResource item = lst.get(0);
            item.setResUrl("/icon/4.icon");

            reg.get().setName("深圳（修改4）");
//            regionDao.save(reg.get());//都会级联修改，特别注意级联删除
            regionRescourceDao.save(item);
//            System.out.println(item.getResUrl());
        }
    }

    @Test
    @Rollback(value = false)
    public void createBankGroup(){
        Optional<Region> region = regionDao.findById("402883e664b6d10e0164b6d13b5c0000");
        if (region.isPresent()) {
            Bank bank1 = new Bank();
            bank1.setBankCode("01001");
            bank1.setBankName("01001");
            bank1.setRegion(region.get());

            Bank bank2 = new Bank();
            bank2.setBankCode("01002");
            bank2.setBankName("01002");
            bank2.setRegion(region.get());

            List<Bank> lstSave = new ArrayList<>();
            lstSave.add(bank1);
            lstSave.add(bank2);

            bankDao.saveAll(lstSave);
        }
    }

    @Test
    public void queryBankByRegion(){
        Region itemReg = regionDao.findById("402883e664b6d10e0164b6d13b5c0000").get();
        List<Bank> lstB = bankDao.findBanksByRegion(itemReg);
        if (lstB.size() > 0) {
            for (Bank item : lstB) {
                System.out.print(item.getBankName());
            }
        }

        Pageable pageable = PageRequest.of(0,1);

        Page<Bank> plstB = bankDao.queryBanksByRegion(itemReg,pageable);
        for (Bank item : plstB) {
            System.out.print(item.getBankName());
        }
    }

    @Test
    public void queryBankByRegionProperty(){
        Region itemReg = regionDao.findById("402883e664b6d10e0164b6d13b5c0000").get();

        Pageable pageable = PageRequest.of(0,1);

        Page<Bank> plstB = bankDao.queryBankByRegion_Code(itemReg.getCode(),pageable);
        for (Bank item : plstB) {
            System.out.print(item.getBankName());
        }
    }

    @Test
    public void queryCustomJdbcRegion(){
//        List<Region> lst = regionDao.customQuery();
        List<Region> lst2 = regionDao.customQueryCriteria();
    }
    @Test
    public void queryCustomRegion(){
        Pageable page = PageRequest.of(0,2);
        Page<RegionCount> plst = regionDao.findRegionCountById("402883e664b6d10e0164b6d13b5c0000",page);
        for (RegionCount item : plst){
            System.out.println(String.format("Region:%s,Count:%d",item.getRegion().getCode(),item.getBankCountNotNull()));
        }
    }

    @Test
    @Rollback(value = false)
    public void modifyRegion(){
        Optional<Region> itemR = regionDao.findRegionByCode("001");
        if (itemR.isPresent()) {
            Region item = itemR.get();
//            item.setLogoUrl(String.format(item.getLogoUrl() + "/%s","modify") );
//            regionDao.save(itemR.get());
            regionDao.modifyLogoUrlById(String.format(item.getLogoUrl() + "/%s","modify"),item.getId());
        }
    }

    @Test
    @Rollback(value = false)
    public void testCreateDefaultBank(){
        Bank bank = new Bank();
    }

    @Test
    @Rollback(value = false)
    public void testBank(){
        Bank bankP = new Bank();
        bankP.setBankCode("01");
        bankP.setBankName("PBank1");

        bankDao.save(bankP);

//        StringUtils
//        Bank bankC1 = new Bank();
//        bankC1.setBankCode("02");
//        bankC1.setBankName("CBank1");
//        bankC1.setParentBank(bankP);
//
//        Bank bankC2 = new Bank();
//        bankC2.setBankCode("03");
//        bankC2.setBankName("CBank2");
//        bankC2.setParentBank(bankP);
//
//        Set<Bank> collBank = new HashSet<>();
//        collBank.add(bankC1);
//        collBank.add(bankC2);
//
//        List<Bank> lstBank = bankDao.saveAll(collBank);

    }

    @Test
    @Rollback(value = false)
    public void testBankTemp(){
        TestBank bankP = new TestBank();
        bankP.setCode("02");

        testBankDao.save(bankP);

        TestBank bankC1 = new TestBank();
        bankC1.setCode("C02");
        bankC1.setParent(bankP);

        TestBank bankC2 = new TestBank();
        bankC2.setCode("C03");
        bankC2.setParent(bankP);

        Set<TestBank> collBank = new HashSet<>();
        collBank.add(bankC1);
        collBank.add(bankC2);

        List<TestBank> lstBank = testBankDao.saveAll(collBank);

    }

    @Test
    @Rollback(value = false)
    public void testOrgTemp(){
        ScOrgInfo bankP = new ScOrgInfo();

        orgDao.save(bankP);

        ScOrgInfo bankC1 = new ScOrgInfo();
        bankC1.setScOrgInfo(bankP);

        ScOrgInfo bankC2 = new ScOrgInfo();
        bankC2.setScOrgInfo(bankP);

        Set<ScOrgInfo> collBank = new HashSet<>();
        collBank.add(bankC1);
        collBank.add(bankC2);

        List<ScOrgInfo> lstBank = orgDao.saveAll(collBank);

    }

}
