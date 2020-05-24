package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Para;
import entity.Result;

import java.util.List;

public interface ParaService {


    /**
     * 根据分类Id查询出对应的分类，再根据分类对应的模板Id查询对应的参数
     * @param categoryId
     * @return
     */
    Result<List<Para>> findByCategoryId(Integer categoryId);

    /**
     * 查询所有参数
     * @return
     */
    Result<List<Para>> findAll();

    /**
     * 分页查询所有参数
     * @param page
     * @param size
     * @return
     */
    Result<List<Para>> findAll(Integer page, Integer size);


    /**
     * 条件查询参数
     * @param para
     * @return
     */
    Result<IPage<Para>> findByCondition(Para para);

    /**
     * 分页+条件查询参数
     * @param para
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Para>> findByCondition(Para para, Integer page, Integer size);


    /**
     * 根据Id查询参数
     * @param id
     * @return
     */
    Result<Para> findById(Integer id);

    /**
     * 新增参数
     * @param para
     * @return
     */
    Result insert(Para para);

    /**
     * 修改参数
     * @param para
     * @return
     */
    Result update(Para para);

    /**
     * 删除参数
     * @param id
     * @return
     */
    Result delete(Integer id);

}
