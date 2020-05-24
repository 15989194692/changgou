package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Category;
import entity.Result;

import java.util.List;

public interface CategoryService {



    /**
     * 根据父类ID查询分类
     * @param parentId
     * @return
     */
    Result<List<Category>> findByParentId(Integer parentId);


    /**
     * 查询所有分类
     * @return
     */
    Result<List<Category>> findAll();

    /**
     * 分页查询所有分类
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Category>> findAll(Integer page, Integer size);

    /**
     * 根据条件查询分类
     * @param category
     * @return
     */
    Result<List<Category>> findByCondition(Category category);


    /**
     * 分页+条件查询分类
     * @param category
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Category>> findByCondition(Category category, Integer page, Integer size);



    /**
     * 根据ID查询分类
     * @param id
     * @return
     */
    Result<Category> findById(Integer id);

    /**
     * 新增分类
     * @param category
     * @return
     */
    Result insert(Category category);


    /**
     * 修改分类
     * @param category
     * @return
     */
    Result update(Category category);


    /**
     * 根据ID删除分类
     * @param id
     * @return
     */
    Result delete(Integer id);
}
