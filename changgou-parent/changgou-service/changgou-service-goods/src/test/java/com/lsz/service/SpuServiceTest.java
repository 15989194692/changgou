package com.lsz.service;

import entity.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpuServiceTest {

    @Autowired
    private SpuService spuService;


    @Test
    public void testPutBatch() {
        List<String> spuIds = new ArrayList<>();

        spuIds.add("10000000616300");
        spuIds.add("10000001516600");

        Result result = spuService.putBatch(spuIds);

        System.out.println("result = " + result);
    }
}
