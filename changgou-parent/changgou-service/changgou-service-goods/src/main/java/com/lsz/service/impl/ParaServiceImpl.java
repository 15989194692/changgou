package com.lsz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsz.dao.CategoryMapper;
import com.lsz.dao.ParaMapper;
import com.lsz.pojo.Category;
import com.lsz.pojo.Para;
import com.lsz.service.ParaService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParaServiceImpl implements ParaService {

    @Autowired
    private ParaMapper paraMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result<List<Para>> findByCategoryId(Integer categoryId) {
        Category category = categoryMapper.selectById(categoryId);

        Para para = new Para();
        para.setTemplateId(category.getTemplateId());

        LambdaQueryWrapper<Para> queryWrapper = getQueryWrapper(para);

        List<Para> paras = paraMapper.selectList(queryWrapper);
        return new Result<>(true, StatusCode.OK, "根据分类Id查询对应的分类，再根据对应分类的模板id，查询参数成功！", paras);
    }

    @Override
    public Result<List<Para>> findAll() {
        LambdaQueryWrapper<Para> queryWrapper = getQueryWrapper(null);

        List<Para> paras = paraMapper.selectList(queryWrapper);

        return new Result<>(true, StatusCode.OK, "查询所有参数成功！", paras);
    }

    @Override
    public Result<List<Para>> findAll(Integer page, Integer size) {
        Page<Para> page1 = new Page<>(page, size);

        LambdaQueryWrapper<Para> queryWrapper = getQueryWrapper(null);

        Page<Para> paraPage = paraMapper.selectPage(page1, queryWrapper);
        return new Result<>(true, StatusCode.OK, "分页查询所有参数成功！", paraPage);
    }

    @Override
    public Result<IPage<Para>> findByCondition(Para para) {
        LambdaQueryWrapper<Para> queryWrapper = getQueryWrapper(para);

        List<Para> paras = paraMapper.selectList(queryWrapper);

        return new Result<>(true, StatusCode.OK, "条件查询参数成功！", paras);
    }

    @Override
    public Result<IPage<Para>> findByCondition(Para para, Integer page, Integer size) {
        Page<Para> para1 = new Page<>(page, size);

        LambdaQueryWrapper<Para> queryWrapper = getQueryWrapper(para);

        Page<Para> paraPage = paraMapper.selectPage(para1, queryWrapper);

        return new Result<>(true, StatusCode.OK, "分页+条件查询参数成功！", paraPage);
    }

    @Override
    public Result<Para> findById(Integer id) {
        if (id == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要查询的参数Id！");
        }
        Para para = paraMapper.selectById(id);
        return new Result<>(true, StatusCode.OK, "根据Id查询参数成功！", para);
    }

    @Override
    public Result insert(Para para) {
        para.setId(null);
        paraMapper.insert(para);
        return new Result<>(true, StatusCode.OK, "新增参数成功！");
    }

    @Override
    public Result update(Para para) {
        if (para.getId() == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要修改的参数Id！");
        }
        paraMapper.updateById(para);
        return new Result<>(true, StatusCode.OK, "修改参数成功！");
    }

    @Override
    public Result delete(Integer id) {
        if (id == null) {
            return new Result<>(false, StatusCode.ERROR, "没有传入要删除的参数Id！");
        }
        paraMapper.deleteById(id);
        return new Result<>(true, StatusCode.OK, "根据Id删除参数成功！");
    }

    private LambdaQueryWrapper<Para> getQueryWrapper(Para para) {
        LambdaQueryWrapper<Para> queryWrapper = Wrappers.lambdaQuery();

        if (para != null) {
            if (para.getId() != null) {
                queryWrapper.eq(Para::getId, para.getId());
            }

            if (para.getTemplateId() != null) {
                queryWrapper.eq(Para::getTemplateId, para.getTemplateId());
            }

            if (StringUtils.isNotBlank(para.getName())) {
                queryWrapper.like(Para::getName, "%" + para.getName() + "%");
            }

            if (StringUtils.isNotBlank(para.getOptions())) {
                queryWrapper.like(Para::getOptions, "%" + para.getOptions() + "%");
            }
        }

        return queryWrapper;
    }
}
