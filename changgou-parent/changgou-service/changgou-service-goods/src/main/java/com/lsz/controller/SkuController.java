package com.lsz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Sku;
import com.lsz.service.SkuService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping
    public Result<List<Sku>> findAll() {
        return skuService.findAll();
    }

    @GetMapping("/search/{page}/{size}")
    public Result<List<Sku>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return skuService.findAll(page, size);
    }

    @PostMapping("/search")
    public Result<IPage<Sku>> findByCondition(@RequestBody Sku sku) {
        return skuService.findByCondition(sku);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Sku>> findByCondition(@RequestBody Sku sku, @PathVariable Integer page, @PathVariable Integer size) {
        return skuService.findByCondition(sku, page, size);
    }


    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable String id) {
        return skuService.findById(id);
    }

    @PostMapping
    public Result insert(@RequestBody Sku sku) {
        return skuService.insert(sku);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody Sku sku) {
        sku.setId(id);
        return skuService.update(sku);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        return skuService.delete(id);
    }
}
