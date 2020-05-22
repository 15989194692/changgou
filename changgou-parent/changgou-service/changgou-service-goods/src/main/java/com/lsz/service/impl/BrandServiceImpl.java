package com.lsz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsz.dao.BrandMapper;
import com.lsz.pojo.Brand;
import com.lsz.service.BrandService;
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
    public IPage<Brand> findPage(Integer page, Integer size) {
        return findPage(null, page, size);
    }

    @Override
    public IPage<Brand> findPage(Brand brand, Integer page, Integer size) {
        LambdaQueryWrapper<Brand> queryWrapper = getQueryWrapper(brand);

        Page<Brand> page1 = new Page(page, size);

        IPage<Brand> pages = brandMapper.selectPage(page1, queryWrapper);

        return pages;
    }

    @Override
    public List<Brand> findList(Brand brand) {
        LambdaQueryWrapper<Brand> queryWrapper = getQueryWrapper(brand);


        return brandMapper.selectList(queryWrapper);
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



    private LambdaQueryWrapper<Brand> getQueryWrapper(Brand brand) {
        LambdaQueryWrapper<Brand> queryWrapper = Wrappers.<Brand>lambdaQuery();

        if (brand != null) {
            if (StringUtils.isNotBlank(brand.getName())) {
                queryWrapper.like(Brand::getName, "%" + brand.getName() + "%");
            }

            if (StringUtils.isNotBlank(brand.getLetter())) {
                queryWrapper.eq(Brand::getLetter, brand.getLetter());
            }

            if (brand.getId() != null) {
                queryWrapper.eq(Brand::getId, brand.getId());
            }

            if (StringUtils.isNotBlank(brand.getImage())) {
                queryWrapper.eq(Brand::getImage, brand.getImage());
            }
        }

        return queryWrapper;
    }


}
