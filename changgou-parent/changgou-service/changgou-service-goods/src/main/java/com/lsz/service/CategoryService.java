package com.lsz.service;

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
