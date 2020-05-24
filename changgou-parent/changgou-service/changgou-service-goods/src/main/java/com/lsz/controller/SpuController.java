package com.lsz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Spu;
import com.lsz.service.SpuService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping
    public Result<List<Spu>> findAll() {
        return spuService.findAll();
    }

    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Spu>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return spuService.findAll(page, size);
    }

    @PostMapping("/search")
    public Result<List<Spu>> findByCondition(@RequestBody Spu spu) {
        return spuService.findByCondition(spu);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Spu>> findByCondition(@RequestBody Spu spu, @PathVariable Integer page, @PathVariable Integer size) {
        return spuService.findByCondition(spu, page, size);
    }

    @GetMapping("{id}")
    public Result<Spu> findById(@PathVariable String id) {
        return spuService.findById(id);
    }

    @PostMapping
    public Result insert(@RequestBody Spu spu) {
        return spuService.insert(spu);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody Spu spu) {
        spu.setId(id);
        return spuService.update(spu);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        return spuService.delete(id);
    }
}
