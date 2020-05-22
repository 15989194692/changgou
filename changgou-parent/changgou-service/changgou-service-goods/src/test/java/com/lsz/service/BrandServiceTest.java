package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Brand;
import com.lsz.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandServiceTest {

    @Autowired
    BrandService brandService;


    @Test
    public void testFindPage() {
        IPage<Brand> page = brandService.findPage(0, 3);

        System.out.println("page = " + page);

        List<Brand> records = page.getRecords();
        System.out.println("records = " + records);

        long total = page.getTotal();
        System.out.println("total = " + total);

        long current = page.getCurrent();
        System.out.println("current = " + current);

        long pages = page.getPages();
        System.out.println("pages = " + pages);

        long size = page.getSize();
        System.out.println("size = " + size);


    }
}
