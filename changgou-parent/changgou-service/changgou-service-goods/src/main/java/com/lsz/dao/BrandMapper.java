package com.lsz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsz.pojo.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 根据分类Id查询品牌集合
     * @param categoryId
     * @return
     */
    @Select("select * from tb_brand tb, tb_category_brand tbc where tb.id = tbc.brand_id and tbc.category_id = #{categoryId}")
    List<Brand> findByCategoryId(Integer categoryId);
}
