package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Brand;
import entity.Page;
import entity.Result;
import io.swagger.models.auth.In;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();


    /**
     * 分页查询所有品牌
     * @param page
     * @param size
     * @return
     */
    IPage<Brand> findPage(Integer page, Integer size);


    /**
     * 分页+条件查询品牌
     * @param brand
     * @param page
     * @param size
     * @return
     */
    IPage<Brand> findPage(Brand brand, Integer page, Integer size);

    /**
     * 根据条件查询品牌
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);






    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 添加品牌信息
     * @param brand
     */
    void addBrand(Brand brand);

    /**
     * 修改品牌信息
     * @param brand
     */
    void updateBrand(Brand brand);


    /**
     * 根据id删除品牌
     * @param id
     */
    void deleteBrand(Integer id);


}
