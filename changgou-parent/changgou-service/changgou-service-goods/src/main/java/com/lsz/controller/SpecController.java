package com.lsz.controller;

import com.lsz.pojo.Spec;
import com.lsz.service.SpecService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;


    public Result<List<Spec>> findByCategoryId(Integer categoryId) {
        return specService.findByCategoryId(categoryId);
    }


}
