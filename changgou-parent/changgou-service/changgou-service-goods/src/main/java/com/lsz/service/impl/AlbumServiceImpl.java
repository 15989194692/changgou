package com.lsz.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lsz.dao.AlbumMapper;
import com.lsz.pojo.Album;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumMapper albumMapper;


    @Override
    public List<Album> findAll() {
        List<Album> albums = albumMapper.selectList(null);

        return albums;
    }

    @Override
    public IPage<Album> findPage(Integer page, Integer size) {
        return null;
    }

    @Override
    public List<Album> findByCondition(Album album) {
        return null;
    }

    @Override
    public IPage<Album> findPageByCondition(Album album) {
        return null;
    }

    @Override
    public void insert(Album album) {

    }

    @Override
    public void update(Album album) {

    }

    @Override
    public void delete(Integer id) {

    }


    /**
     * 获取查询wrapper
     * @param album
     * @return
     */
    private LambdaQueryWrapper<Album> getQueryWrapper(Album album) {
        LambdaQueryWrapper<Album> queryWrapper = Wrappers.<Album>lambdaQuery();

        if (album != null) {
            if (album.getId() != null) {
                queryWrapper.eq(Album::getId, album.getId());
            }

            if (StringUtils.isNotBlank(album.getTitle())) {
                queryWrapper.like(Album::getTitle, "%" + album.getTitle() + "%");
            }

            if (StringUtils.isNotBlank(album.getImage())) {
                queryWrapper.eq(Album::getImage, album.getImage());
            }

            if 
        }


        return queryWrapper;
    }
}
