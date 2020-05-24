package com.lsz.controller;

import com.lsz.pojo.Spec;
import com.lsz.service.SpecService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;


    @GetMapping("/category/{id}")
    public Result<List<Spec>> findByCategoryId(@PathVariable("id") Integer categoryId) {
        return specService.findByCategoryId(categoryId);
    }


}
