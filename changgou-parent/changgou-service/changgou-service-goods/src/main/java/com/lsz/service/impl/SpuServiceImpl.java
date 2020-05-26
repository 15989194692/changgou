package com.lsz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsz.dao.BrandMapper;
import com.lsz.dao.CategoryMapper;
import com.lsz.dao.SkuMapper;
import com.lsz.dao.SpuMapper;
import com.lsz.pojo.*;
import com.lsz.service.SpuService;
import com.sun.xml.internal.rngom.digested.DElementPattern;
import util.IdWorker;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService {


    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;


    private String DELETE = "1";
    private String NOT_DELETE = "0";
    private String PUT_MARKETTABLE = "1";
    private String PULL_MARKETTABLE = "0";
    private String AUDIT = "1";
    private String NOT_AUDIT = "0";

    private String SKU_DELETE = "3";



    @Override
    public Result deleteGoods(String spuId) {
        //逻辑删除spu属性
        LambdaUpdateWrapper<Spu> spuLambdaUpdateWrapper = Wrappers.lambdaUpdate();
        spuLambdaUpdateWrapper.eq(Spu::getId, spuId);

        Spu spu = new Spu();
        spu.setIsMarketable(PULL_MARKETTABLE);
        spu.setStatus(NOT_AUDIT);
        spu.setIsDelete(DELETE);

        spuMapper.update(spu, spuLambdaUpdateWrapper);

        //逻辑删除skuList属性，根据spuId删除
        LambdaUpdateWrapper<Sku> skuLambdaUpdateWrapper = Wrappers.lambdaUpdate();
        skuLambdaUpdateWrapper.eq(Sku::getSpuId, spuId);

        Sku sku = new Sku();
        sku.setStatus(SKU_DELETE);

        skuMapper.update(sku, skuLambdaUpdateWrapper);



        return new Result(true, StatusCode.OK, "根据spuId逻辑删除商品成功！");
    }

    @Override
    public Result putBatch(List<String> spuIds) {
        LambdaUpdateWrapper<Spu> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(Spu::getId, spuIds);
        updateWrapper.eq(Spu::getIsDelete, NOT_DELETE);
        updateWrapper.eq(Spu::getStatus, AUDIT);

        Spu spu = new Spu();
        spu.setIsMarketable(PUT_MARKETTABLE);

        spuMapper.update(spu, updateWrapper);

        return new Result(true, StatusCode.OK, "批量上架成功！");
    }

    @Override
    public Result put(String spuId) {
        Spu spu = spuMapper.selectById(spuId);

        if (DELETE.equals(spu.getIsDelete())) {
            throw new RuntimeException("不能对已删除的商品进行上架操作！");
        }

        if (!AUDIT.equals(spu.getStatus())) {
            throw new RuntimeException("不能对未审核的商品进行上架操作！");
        }

        spu.setIsMarketable(PUT_MARKETTABLE);

        spuMapper.updateById(spu);
        return new Result(true, StatusCode.OK, "商品上架成功！");
    }

    @Override
    public Result pull(String spuId) {
        Spu spu = spuMapper.selectById(spuId);

        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("不能对已经删除的商品进行下架操作！");
        }

        spu.setIsMarketable(PULL_MARKETTABLE);

        spuMapper.updateById(spu);

        return new Result(true, StatusCode.OK, "商品下架成功！");
    }

    @Override
    public Result audit(String spuId) {
        Spu spu = spuMapper.selectById(spuId);

        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("不能对已删除的商品进行审核操作！");
        }

        spu.setIsMarketable(PUT_MARKETTABLE);
        spu.setStatus(AUDIT);

        spuMapper.updateById(spu);

        return new Result(true, StatusCode.OK, "审核商品状态成功！");
    }

    @Override
    public Result saveGoods(Goods goods) {

        Spu spu = goods.getSpu();
        if (spu.getId() == null) {
            spu.setId(String.valueOf(idWorker.nextId()));
            spuMapper.insert(spu);
        } else{
            LambdaQueryWrapper<Sku> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(Sku::getSpuId, spu.getId());
            skuMapper.delete(queryWrapper);

            spuMapper.updateById(spu);
        }

        List<Sku> skuList = goods.getSkuList();

        Brand brand = brandMapper.selectById(spu.getBrandId());
        Category category = categoryMapper.selectById(spu.getCategory3Id());


        skuList.stream().forEach(sku -> {
            sku.setId(String.valueOf(idWorker.nextId()));
            sku.setSpuId(spu.getId());
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());

            String name = spu.getName();

            if (StringUtils.isEmpty(sku.getSpec())) {
                sku.setSpec("{}");
            }

            Map<String, String> map = JSON.parseObject(sku.getSpec(), Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                name += " " + entry.getValue();
            }
            sku.setName(name);

            skuMapper.insert(sku);
        });


        /*for (Sku sku : skuList) {
            sku.setId(String.valueOf(idWorker.nextId()));
            sku.setSpuId(spu.getId());

            String name = spu.getName();

            if (StringUtils.isEmpty(sku.getSpec())) {
                sku.setSpec("{}");
            }

            Map<String, String> map = JSON.parseObject(sku.getSpec(), Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                name += " " + entry.getValue();
            }

            sku.setName(name);
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());

        }*/

        return new Result(true, StatusCode.OK, "新增商品成功！");
    }

    @Override
    public Result<Goods> findGoodsById(String spuId) {
        //根据Id查询商品的spu属性
        Spu spu = spuMapper.selectById(spuId);
        //根据spu的Id查询商品的skuList属性
        LambdaQueryWrapper<Sku> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Sku::getSpuId, spuId);
        List<Sku> skuList = skuMapper.selectList(queryWrapper);
        //构造一个bean装入
        Goods goods = new Goods(spu, skuList);

        return new Result<>(true, StatusCode.OK, "根据spuId查询商品的spu和skuList属性成功！", goods);
    }

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
    public Result<Spu> findById(String id) {
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
    public Result delete(String id) {
        if (id == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要删除的商品公共属性Id！");
        }
        spuMapper.deleteById(id);
        return new Result(true, StatusCode.OK, "新增商品公共属性成功！");
    }


    private LambdaQueryWrapper<Spu> getQueryWrapper(Spu spu) {
        LambdaQueryWrapper<Spu> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.ne(Spu::getIsDelete, DELETE);

        if (spu != null) {
            if (StringUtils.isNotBlank(spu.getId())) {
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
