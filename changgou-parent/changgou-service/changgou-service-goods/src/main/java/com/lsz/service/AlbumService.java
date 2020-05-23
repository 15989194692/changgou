package com.lsz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Album;
import entity.Result;

import java.util.List;

public interface AlbumService {

    /**
     * 查询所有相册
     * @return
     */
    Result<List<Album>> findAll();


    /**
     * 分页查询所有
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Album>> findPage(Integer page, Integer size);

    /**
     * 根据条件查询
     * @param album
     * @return
     */
    Result<List<Album>> findByCondition(Album album);

    /**
     * 根据条件+分页查询
     * @param album
     * @param page
     * @param size
     * @return
     */
    Result<IPage<Album>> findPageByCondition(Album album, Integer page, Integer size);

    /**
     * 根据ID查询相册
     * @param id
     * @return
     */
    Result<Album> findById(Long id);


    /**
     * 新增相册
     * @param album
     */
    Result insert(Album album);

    /**
     * 修改相册
     * @param album
     */
    Result update(Album album);


    /**
     * 根据id删除相册
     * @param id
     */
    Result delete(Long id);


}
