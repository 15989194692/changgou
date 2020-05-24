package com.lsz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Para;
import com.lsz.service.ParaService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {

    @Autowired
    private ParaService paraService;

    @GetMapping("/category/{id}")
    public Result<List<Para>> findByCategoryId(@PathVariable("id") Integer categoryId) {
        return paraService.findByCategoryId(categoryId);
    }

    @GetMapping
    public Result<List<Para>> findAll() {
        return paraService.findAll();
    }

    @GetMapping("/search/{page}/{size}")
    public Result<List<Para>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return paraService.findAll(page, size);
    }

    @PostMapping("/search")
    public Result<IPage<Para>> findByCondition(@RequestBody Para para) {
        return paraService.findByCondition(para);
    }


    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Para>> findByCondition(@RequestBody Para para, @PathVariable Integer page, @PathVariable Integer size) {
        return paraService.findByCondition(para, page, size);
    }

    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable Integer id) {
        return paraService.findById(id);
    }


    @PostMapping
    public Result insert(@RequestBody Para para) {
        return paraService.insert(para);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Para para) {
        return paraService.update(para);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return paraService.delete(id);
    }

}
