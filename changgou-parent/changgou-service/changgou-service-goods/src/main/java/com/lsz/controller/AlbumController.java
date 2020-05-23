package com.lsz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Album;
import com.lsz.service.AlbumService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping
    public Result<List<Album>> findAll() {
        return albumService.findAll();
    }


    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Album>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        return albumService.findPage(page, size);
    }

    @PostMapping("/search")
    public Result<List<Album>> findAll(@RequestBody Album album) {
        return albumService.findByCondition(album);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Album>> findPage(@RequestBody Album album, @PathVariable Integer page, @PathVariable Integer size) {
        return albumService.findPageByCondition(album, page, size);
    }

    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable Long id) {
        return albumService.findById(id);
    }

    @PostMapping
    public Result insert(@RequestBody Album album) {
        return albumService.insert(album);
    }


    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Album album) {
        album.setId(id);
        return albumService.update(album);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return albumService.delete(id);
    }



}
