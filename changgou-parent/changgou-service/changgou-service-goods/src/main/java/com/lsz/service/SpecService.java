package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Spec;
import entity.Result;

import java.util.List;

public interface SpecService {


    /**
     * 根据分类id查询出分类对应的模板id，再根据模板id查询参数
     * @param categoryId
     * @return
     */
    Result<List<Spec>> findByCategoryId(Integer categoryId);

    /**
     * 查询所有参数列表
     * @return
     */
    Result<List<Spec>> findAll();


    /**
     * 根据条件查询参数列表
     * @param spec
     * @return
     */
    Result<List<Spec>> findByCondition(Spec spec);


    /**
     * 分页查询所有参数列表
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Spec>> findAll(Integer page, Integer size);


    /**
     * 分页+条件查询参数列表
     * @param spec
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Spec>> findByCondition(Spec spec, Integer page, Integer size);


    /**
     * 根据Id查询参数
     * @param id
     * @return
     */
    Result<Spec> findById(Integer id);


    /**
     * 新增参数
     * @param spec
     * @return
     */
    Result insert(Spec spec);


    /**
     * 修改参数
     * @param spec
     * @return
     */
    Result update(Spec spec);


    /**
     * 根据Id删除参数
     * @param id
     * @return
     */
    Result delete(Integer id);
}
