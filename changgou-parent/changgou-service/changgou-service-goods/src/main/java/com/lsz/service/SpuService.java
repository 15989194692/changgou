package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Goods;
import com.lsz.pojo.Spu;
import entity.Result;

import java.util.List;

public interface SpuService {


    /**
     * 根据spuId删除商品
     * @param spuId
     * @return
     */
    public Result deleteGoods(String spuId);

    /**
     * 批量上架
     * @param spuIds
     * @return
     */
    public Result putBatch(List<String> spuIds);

    /**
     * 商品上架
     * @param spuId
     * @return
     */
    public Result put(String spuId);

    /**
     * 商品下架
     * @param spuId
     * @return
     */
    public Result pull(String spuId);

    /**
     * 审核：根据spuId修改商品的状态
     * @param spuId
     * @return
     */
    public Result audit(String spuId);

    /**
     * 新增商品信息
     * @param goods
     * @return
     */
    Result saveGoods(Goods goods);

    /**
     * 根据商品id查询商品的spu和skuList属性
     * @param spuId
     * @return
     */
    Result<Goods> findGoodsById(String spuId);

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
    Result<Spu> findById(String id);

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
    Result delete(String id);
}
