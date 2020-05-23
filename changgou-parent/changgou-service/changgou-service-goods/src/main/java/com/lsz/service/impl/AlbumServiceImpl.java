package com.lsz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsz.dao.AlbumMapper;
import com.lsz.pojo.Album;
import com.lsz.service.AlbumService;
import entity.Result;
import entity.StatusCode;
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
    public Result<List<Album>> findAll() {
        List<Album> albums = albumMapper.selectList(null);

        return new Result<List<Album>>(true, StatusCode.OK, "查询所有相册成功！", albums);
    }

    @Override
    public Result<IPage<Album>> findPage(Integer page, Integer size) {
        Page<Album> page1 = new Page<>(page, size);

        Page<Album> albumPage = albumMapper.selectPage(page1, null);

        return new Result<IPage<Album>>(true, StatusCode.OK, "分页查询所有相册成功！", albumPage);
    }

    @Override
    public Result<List<Album>> findByCondition(Album album) {
        LambdaQueryWrapper<Album> queryWrapper = getQueryWrapper(album);

        List<Album> albums = albumMapper.selectList(queryWrapper);
        return new Result<List<Album>>(true, StatusCode.OK, "条件查询相册成功！", albums);
    }

    @Override
    public Result<IPage<Album>> findPageByCondition(Album album, Integer page, Integer size) {
        Page<Album> page1 = new Page<>(page, size);

        LambdaQueryWrapper<Album> queryWrapper = getQueryWrapper(album);

        Page<Album> albumPage = albumMapper.selectPage(page1, queryWrapper);

        return new Result<IPage<Album>>(true, StatusCode.OK, "条件+分页查询相册成功！", albumPage);
    }

    @Override
    public Result<Album> findById(Long id) {
        Album album = albumMapper.selectById(id);

        return new Result<Album>(true, StatusCode.OK, "根据ID查询相册成功！", album);
    }


    @Override
    public Result insert(Album album) {
        album.setId(null);
        albumMapper.insert(album);
        return new Result(true, StatusCode.OK, "新增相册成功！");
    }

    @Override
    public Result update(Album album) {
        if (album.getId() == null) {
            return new Result(false, StatusCode.ERROR, "没有传入要修改的相册ID！");
        }
        albumMapper.updateById(album);
        return new Result(true, StatusCode.OK, "修改相册成功！");
    }

    @Override
    public Result delete(Long id) {
        if (id == null) {
            return new Result(false, StatusCode.ERROR, "没有传入要删除的相册ID！");
        }
        albumMapper.deleteById(id);
        return new Result(true, StatusCode.OK, "删除相册成功！");
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
        }


        return queryWrapper;
    }
}
