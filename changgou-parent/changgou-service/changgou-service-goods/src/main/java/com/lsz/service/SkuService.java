package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Sku;
import entity.Result;

import java.util.List;

public interface SkuService {

    /**
     * 查询所有商品特有属性
     * @return
     */
    Result<List<Sku>> findAll();

    /**
     * 分页查询所有商品特有属性
     * @param page
     * @param size
     * @return
     */
    Result<List<Sku>> findAll(Integer page, Integer size);


    /**
     * 条件查询所有商品特有属性
     * @param sku
     * @return
     */
    Result<IPage<Sku>> findByCondition(Sku sku);

    /**
     * 分页+条件查询商品特有属性
     * @param sku
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Sku>> findByCondition(Sku sku, Integer page, Integer size);

    /**
     * 根据Id查询商品特有属性
     * @param id
     * @return
     */
    Result<Sku> findById(String id);

    /**
     * 新增商品特有属性
     * @param sku
     * @return
     */
    Result insert(Sku sku);

    /**
     * 修改商品特有属性
     * @param sku
     * @return
     */
    Result update(Sku sku);

    /**
     * 根据Id删除商品特有属性
     * @param id
     * @return
     */
    Result delete(String id);

}
