package com.lsz.service;

import com.lsz.pojo.Sku;
import entity.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkuServiceTest {

    @Autowired
    private SkuService skuService;

    @Test
    public void testFindAll() {
        Result<List<Sku>> all = skuService.findAll();
        System.out.println("all = " + all.getData());
    }
}
