package com.lsz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsz.dao.CategoryMapper;
import com.lsz.dao.SpecMapper;
import com.lsz.pojo.Category;
import com.lsz.pojo.Spec;
import com.lsz.service.SpecService;
import entity.Result;
import entity.StatusCode;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public Result<List<Spec>> findByCategoryId(Integer categoryId) {
        Category category = categoryMapper.selectById(categoryId);

        Spec spec = new Spec();
        spec.setTemplateId(category.getTemplateId());

        LambdaQueryWrapper<Spec> queryWrapper = getQueryWrapper(spec);

        List<Spec> specs = specMapper.selectList(queryWrapper);

        return new Result<List<Spec>>(true, StatusCode.OK, "根据分类id查询对应分类的模板id，再根据模板id查询规格成功！", specs);
    }

    @Override
    public Result<List<Spec>> findAll() {
        List<Spec> specs = specMapper.selectList(null);
        return new Result<List<Spec>>(true, StatusCode.OK, "查询所有规格成功！", specs);
    }

    @Override
    public Result<List<Spec>> findByCondition(Spec spec) {
        LambdaQueryWrapper<Spec> queryWrapper = getQueryWrapper(spec);
        List<Spec> specs = specMapper.selectList(queryWrapper);
        return new Result<List<Spec>>(true, StatusCode.OK, "根据条件查询规格成功！", specs);
    }

    @Override
    public Result<IPage<Spec>> findAll(Integer page, Integer size) {
        Page<Spec> page1 = new Page<>(page, size);

        Page<Spec> specPage = specMapper.selectPage(page1, null);
        return new Result<IPage<Spec>>(true, StatusCode.OK, "分页查询所有规格成功！", specPage);
    }

    @Override
    public Result<IPage<Spec>> findByCondition(Spec spec, Integer page, Integer size) {
        Page<Spec> page1 = new Page<>(page, size);
        LambdaQueryWrapper<Spec> queryWrapper = getQueryWrapper(spec);
        Page<Spec> specPage = specMapper.selectPage(page1, queryWrapper);

        return new Result<IPage<Spec>>(true, StatusCode.OK, "分页+条件查询规格成功！", specPage);
    }

    @Override
    public Result<Spec> findById(Integer id) {
        if (id == null) {
            new Result<>(false, StatusCode.ERROR, "没有传入要查询的规格Id！");
        }
        Spec spec = specMapper.selectById(id);

        return new Result<Spec>(true, StatusCode.OK, "根据Id查询规格成功！", spec);
    }

    @Override
    public Result insert(Spec spec) {
        spec.setId(null);

        specMapper.insert(spec);
        return new Result(true, StatusCode.OK, "新增规格成功！");
    }

    @Override
    public Result update(Spec spec) {
        if (spec.getId() == null) {
            new Result<>(false, StatusCode.ERROR, "没有传入要修改的规格Id！");
        }
        specMapper.updateById(spec);
        return new Result(true, StatusCode.OK, "修改规格成功！");
    }

    @Override
    public Result delete(Integer id) {
        if (id == null) {
            new Result<>(false, StatusCode.ERROR, "没有传入要删除的规格Id！");
        }
        specMapper.deleteById(id);
        return new Result(true, StatusCode.OK, "删除规格成功！");
    }


    private LambdaQueryWrapper<Spec> getQueryWrapper(Spec spec) {
        LambdaQueryWrapper<Spec> queryWrapper = Wrappers.lambdaQuery();

        if (spec != null) {
            if (spec.getId() != null) {
                queryWrapper.eq(Spec::getId, spec.getId());
            }

            if (StringUtils.isNotBlank(spec.getName())) {
                queryWrapper.like(Spec::getName, "%" + spec.getName() + "%");
            }

            if (StringUtils.isNotBlank(spec.getOptions())) {
                queryWrapper.like(Spec::getOptions, "%" + spec.getOptions() + "%");
            }

            if (spec.getTemplateId() != null) {
                queryWrapper.eq(Spec::getTemplateId, spec.getTemplateId());
            }

            if (spec.getSeq() != null) {
                queryWrapper.eq(Spec::getSeq, spec.getSeq());
            }
        }

        return queryWrapper;
    }
}
