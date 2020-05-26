package com.lsz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsz.pojo.Brand;
import com.lsz.service.BrandService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin //跨域
@Api(tags = "品牌管理相关接口")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @PostMapping("/search")
    @ApiOperation("根据条件查询品牌")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> list = brandService.findList(brand);

        return new Result<List<Brand>>(true, StatusCode.OK, "根据条件查询品牌成功！", list);
    }

    @ApiOperation("分页查询所有品牌")
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Brand>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Brand> page1 = brandService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询品牌成功！", page1);
    }


    @ApiOperation("分页+条件查询品牌")
    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Brand>> findPage(@RequestBody Brand brand, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Brand> page1 = brandService.findPage(brand, page, size);
        return new Result<>(true, StatusCode.OK, "分页条件查询品牌成功！", page1);
    }


    @ApiOperation("查询所有品牌")
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> allBrands = brandService.findAll();

        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌集合成功！", allBrands);
    }


    @ApiOperation("根据Id查询品牌")
    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "根据id查询品牌成功！", brand);
    }


    @ApiOperation("新增品牌")
    @PostMapping
    public Result insert(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return new Result(true, StatusCode.OK, "添加品牌成功！");
    }


    @ApiOperation("修改品牌")
    @PutMapping("/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true, StatusCode.OK, "修改品牌成功！");
    }


    @ApiOperation("根据Id删除品牌")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return new Result(true, StatusCode.OK, "删除品牌成功！");
    }


    @ApiOperation("根据Id查询品牌")
    @GetMapping("/category/{id}")
    public Result<List<Brand>> findByCategoryId(@PathVariable(value = "id") Integer categoryId) {
        return brandService.findByCategoryId(categoryId);
    }

}
