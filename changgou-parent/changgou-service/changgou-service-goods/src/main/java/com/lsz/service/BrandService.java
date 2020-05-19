package com.lsz.service;

import com.lsz.pojo.Brand;
import entity.Result;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();


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

    /**
     * 根据条件查询品牌
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);
}
