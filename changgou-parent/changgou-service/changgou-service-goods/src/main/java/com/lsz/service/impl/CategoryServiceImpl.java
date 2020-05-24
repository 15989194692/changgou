package com.lsz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lsz.dao.CategoryMapper;
import com.lsz.pojo.Category;
import com.lsz.service.CategoryService;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public Result<List<Category>> findByParentId(Integer parentId) {
        Category category = new Category();
        category.setParentId(parentId);
        LambdaQueryWrapper<Category> queryWrapper = getQueryWrapper(category);

        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return new Result<List<Category>>(true, StatusCode.OK, "根据父类Id查询分类成功！", categories);
    }

    @Override
    public Result<List<Category>> findAll() {
        List<Category> categories = categoryMapper.selectList(null);
        return new Result<List<Category>>(true, StatusCode.OK, "查询所有分类成功！", categories);
    }

    @Override
    public Result<List<Category>> findByCondition(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = getQueryWrapper(category);

        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return new Result<List<Category>>(true, StatusCode.OK, "根据条件查询分类成功！", categories);
    }

    @Override
    public Result<Category> findById(Integer id) {
        Category category = categoryMapper.selectById(id);
        return new Result<Category>(true, StatusCode.OK, "根据Id查询分类成功！", category);
    }

    @Override
    public Result insert(Category category) {
        category.setId(null);
        int insert = categoryMapper.insert(category);
        if (insert < 1) {
            return new Result(false, StatusCode.ERROR, "新增分类失败！");
        }
        return new Result(true, StatusCode.OK, "新增分类成功！");
    }

    @Override
    public Result update(Category category) {
        if (category.getId() == null) {
            return new Result(false, StatusCode.ERROR, "没有传入要修改的分类Id！");
        }
        categoryMapper.updateById(category);

        return new Result(true, StatusCode.OK, "修改分类成功！");
    }

    @Override
    public Result delete(Integer id) {
        if (id == null) {
            return new Result(false, StatusCode.ERROR, "没有传入要删除的分类Id！");
        }
        categoryMapper.deleteById(id);
        return new Result(true, StatusCode.OK, "删除分类成功！");
    }


    /**
     * 根据查询条件构造QueryWrapper
     * @param category
     * @return
     */
    private LambdaQueryWrapper<Category> getQueryWrapper(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.<Category>lambdaQuery();

        if (category != null) {
            if (category.getId() != null) {
                queryWrapper.eq(Category::getId, category.getId());
            }

            if (StringUtils.isNotBlank(category.getName())) {
                queryWrapper.like(Category::getName, "%" + category.getName() + "%");
            }

            if (category.getGoodsNum() != null) {
                queryWrapper.eq(Category::getGoodsNum, category.getGoodsNum());
            }

            if (category.getParentId() != null) {
                queryWrapper.eq(Category::getParentId, category.getParentId());
            }

            if (category.getSeq() != null) {
                queryWrapper.eq(Category::getSeq, category.getSeq());
            }

        }

        return queryWrapper;
    }
}
