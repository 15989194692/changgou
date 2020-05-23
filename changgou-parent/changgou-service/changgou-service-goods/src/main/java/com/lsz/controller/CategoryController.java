package com.lsz.controller;

import com.lsz.pojo.Category;
import com.lsz.service.CategoryService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryService categoryService;


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
