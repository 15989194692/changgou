package com.lsz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lsz.dao.BrandMapper;
import com.lsz.pojo.Brand;
import com.lsz.service.BrandService;
import entity.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectList(null);
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectById(id);
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateById(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteById(id);
    }

    @Override
    public List<Brand> findList(Brand brand) {
        LambdaQueryWrapper<Brand> lambdaQueryWrapper = Wrappers.<Brand>lambdaQuery();

        if (brand != null) {
            if (StringUtils.isNotBlank(brand.getName())) {
                lambdaQueryWrapper.like(Brand::getName, "%" + brand.getName() + "%");
            }

            if (StringUtils.isNotBlank(brand.getLetter())) {
                lambdaQueryWrapper.eq(Brand::getLetter, brand.getLetter());
            }
        }


        return brandMapper.selectList(lambdaQueryWrapper);
    }
}
