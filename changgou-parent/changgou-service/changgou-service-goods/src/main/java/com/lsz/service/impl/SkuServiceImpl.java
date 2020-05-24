package com.lsz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsz.dao.SkuMapper;
import com.lsz.pojo.Sku;
import com.lsz.service.SkuService;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    private Integer STATUS_DELETE = 3;

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public Result<List<Sku>> findAll() {
        LambdaQueryWrapper<Sku> queryWrapper = getQueryWrapper(null);

        List<Sku> skus = skuMapper.selectList(queryWrapper);

        return new Result<>(true, StatusCode.OK, "查询所有商品特有属性成功！", skus);
    }

    @Override
    public Result<List<Sku>> findAll(Integer page, Integer size) {
        LambdaQueryWrapper<Sku> queryWrapper = getQueryWrapper(null);

        Page<Sku> page1 = new Page<Sku>(page, size);

        Page<Sku> skuPage = skuMapper.selectPage(page1, queryWrapper);
        return new Result<>(true, StatusCode.OK, "分页查询所有商品特有属性成功！", skuPage);
    }

    @Override
    public Result<IPage<Sku>> findByCondition(Sku sku) {
        LambdaQueryWrapper<Sku> queryWrapper = getQueryWrapper(sku);

        List<Sku> skus = skuMapper.selectList(queryWrapper);
        return new Result<>(true, StatusCode.OK, "条件查询商品特有属性成功！", skus);
    }

    @Override
    public Result<IPage<Sku>> findByCondition(Sku sku, Integer page, Integer size) {
        LambdaQueryWrapper<Sku> queryWrapper = getQueryWrapper(sku);

        Page<Sku> page1 = new Page<>(page, size);

        Page<Sku> skuPage = skuMapper.selectPage(page1, queryWrapper);
        return new Result<>(true, StatusCode.OK, "分页+条件查询商品特有属性成功！", skuPage);
    }

    @Override
    public Result<Sku> findById(String id) {
        if (id == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要查询的商品特有属性Id！");
        }
        Sku sku = skuMapper.selectById(id);
        return new Result<>(true, StatusCode.OK, "根据Id查询商品特有属性成功！", sku);
    }

    @Override
    public Result insert(Sku sku) {
        sku.setId(null);
        skuMapper.insert(sku);
        return new Result(true, StatusCode.OK, "新增商品特有属性成功！");
    }

    @Override
    public Result update(Sku sku) {
        if (sku.getId() == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要修改的商品特有属性Id！");
        }
        skuMapper.updateById(sku);
        return new Result(true, StatusCode.OK, "修改商品特有属性成功！");
    }

    @Override
    public Result delete(String id) {
        if (id == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要删除的商品特有属性Id！");
        }
        skuMapper.deleteById(id);
        return new Result(true, StatusCode.OK, "根据Id删除商品特有属性成功！");
    }


    private LambdaQueryWrapper<Sku> getQueryWrapper(Sku sku) {
        LambdaQueryWrapper<Sku> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.ne(Sku::getStatus, STATUS_DELETE);

        if (sku != null) {
            if (StringUtils.isNotBlank(sku.getId())) {
                queryWrapper.eq(Sku::getId, sku.getId());
            }

            if (sku.getCategoryId() != null) {
                queryWrapper.eq(Sku::getCategoryId, sku.getCategoryId());
            }

            if (sku.getSpuId() != null) {
                queryWrapper.eq(Sku::getSpuId, sku.getSpuId());
            }

            if (StringUtils.isNotBlank(sku.getBrandName())) {
                queryWrapper.like(Sku::getBrandName, "%" + sku.getBrandName() + "%");
            }

            if (StringUtils.isNotBlank(sku.getCategoryName())) {
                queryWrapper.like(Sku::getCategoryName, "%" + sku.getCategoryName() + "%");
            }

            if (StringUtils.isNotBlank(sku.getName())) {
                queryWrapper.like(Sku::getName, "%" + sku.getName() + "%");
            }

        }


        return queryWrapper;
    }
}
