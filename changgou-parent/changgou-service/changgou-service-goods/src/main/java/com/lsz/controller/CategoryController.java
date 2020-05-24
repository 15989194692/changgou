package com.lsz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Category;
import com.lsz.service.CategoryService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list/{pid}")
    public Result<List<Category>> findByParentId(@PathVariable(value = "pid") Integer parentId) {
        return categoryService.findByParentId(parentId);
    }

    @GetMapping
    public Result<List<Category>> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Category>> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return categoryService.findAll(page, size);
    }


    @PostMapping("/search")
    public Result<List<Category>> findByCondition(@RequestBody Category category) {
        return categoryService.findByCondition(category);
    }


    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Category>> findByCondition(@RequestBody Category category, @PathVariable Integer page, @PathVariable Integer size) {
        return categoryService.findByCondition(category, page, size);
    }


    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public Result insert(@RequestBody Category category) {
        return categoryService.insert(category);
    }

    @PostMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        return categoryService.update(category);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}
