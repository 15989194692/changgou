package com.lsz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsz.dao.SpuMapper;
import com.lsz.pojo.Spu;
import com.lsz.service.SpuService;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    SpuMapper spuMapper;


    @Override
    public Result<List<Spu>> findAll() {
        LambdaQueryWrapper<Spu> queryWrapper = getQueryWrapper(null);
        List<Spu> spus = spuMapper.selectList(queryWrapper);

        return new Result<>(true, StatusCode.OK, "查询所有商品公共属性成功！", spus);
    }

    @Override
    public Result<List<Spu>> findByCondition(Spu spu) {
        LambdaQueryWrapper<Spu> queryWrapper = getQueryWrapper(spu);
        List<Spu> spus = spuMapper.selectList(queryWrapper);

        return new Result<>(true, StatusCode.OK, "条件查询商品公共属性成功！", spus);
    }

    @Override
    public Result<IPage<Spu>> findAll(Integer page, Integer size) {
        Page<Spu> page1 = new Page<>(page, size);

        LambdaQueryWrapper<Spu> queryWrapper = getQueryWrapper(null);

        Page<Spu> spuPage = spuMapper.selectPage(page1, queryWrapper);

        return new Result<>(true, StatusCode.OK, "分页查询所有商品公共属性成功！", spuPage);
    }

    @Override
    public Result<IPage<Spu>> findByCondition(Spu spu, Integer page, Integer size) {
        Page<Spu> page1 = new Page<>(page, size);

        LambdaQueryWrapper<Spu> queryWrapper = getQueryWrapper(spu);

        Page<Spu> spuPage = spuMapper.selectPage(page1, queryWrapper);

        return new Result<>(true, StatusCode.OK, "分页+条件查询商品公共属性成功！", spuPage);
    }

    @Override
    public Result<Spu> findById(Integer id) {
        if (id == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要查询的商品公共属性Id！");
        }
        return null;
    }

    @Override
    public Result insert(Spu spu) {
        spu.setId(null);
        spuMapper.insert(spu);
        return new Result(true, StatusCode.OK, "新增商品公共属性成功！");
    }

    @Override
    public Result update(Spu spu) {
        if (spu.getId() == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要修改的商品公共属性Id！");
        }
        spuMapper.updateById(spu);
        return new Result(true, StatusCode.OK, "新增商品公共属性成功！");
    }

    @Override
    public Result delete(Integer id) {
        if (id == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要删除的商品公共属性Id！");
        }
        spuMapper.deleteById(id);
        return new Result(true, StatusCode.OK, "新增商品公共属性成功！");
    }


    private LambdaQueryWrapper<Spu> getQueryWrapper(Spu spu) {
        LambdaQueryWrapper<Spu> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Spu::getIsDelete, 0);

        if (spu != null) {
            if (spu.getId() != null) {
                queryWrapper.eq(Spu::getId, spu.getId());
            }

            if (spu.getBrandId() != null) {
                queryWrapper.eq(Spu::getBrandId, spu.getBrandId());
            }

            if (StringUtils.isNotBlank(spu.getCaption())) {
                queryWrapper.like(Spu::getCaption, "%" + spu.getCaption() + "%");
            }

            if (StringUtils.isNotBlank(spu.getIntroduction())) {
                queryWrapper.like(Spu::getIntroduction, "%" + spu.getIntroduction() + "%");
            }

            if (spu.getCategory1Id() != null) {
                queryWrapper.eq(Spu::getCategory1Id, spu.getCategory1Id());
            }

            if (spu.getCategory2Id() != null) {
                queryWrapper.eq(Spu::getCategory2Id, spu.getCategory2Id());
            }

            if (spu.getCategory3Id() != null) {
                queryWrapper.eq(Spu::getCategory3Id, spu.getCategory3Id());
            }

        }

        return queryWrapper;
    }
}
