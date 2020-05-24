package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Spu;
import entity.Result;

import java.util.List;

public interface SpuService {


    /**
     * 查询所有商品公共属性
     * @return
     */
    Result<List<Spu>> findAll();

    /**
     * 条件查询商品公共属性
     * @param spu
     * @return
     */
    Result<List<Spu>> findByCondition(Spu spu);


    /**
     * 分页查询所有商品公共属性
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Spu>> findAll(Integer page, Integer size);


    /**
     * 分页+条件查询所有商品公共属性
     * @param spu
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Spu>> findByCondition(Spu spu, Integer page, Integer size);

    /**
     * 根据Id查询商品公共属性
     * @param id
     * @return
     */
    Result<Spu> findById(Integer id);

    /**
     * 新增商品公共属性
     * @param spu
     * @return
     */
    Result insert(Spu spu);


    /**
     * 修改商品公共属性
     * @param spu
     * @return
     */
    Result update(Spu spu);


    /**
     * 根据Id删除商品公共属性
     * @param id
     * @return
     */
    Result delete(Integer id);
}
