package com.lsz.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_brand")
@Data
public class Brand implements Serializable {
    @Id
    @ApiModelProperty("品牌id")
    private Integer id;//品牌id
    @ApiModelProperty("品牌名称")
    private String name;//品牌名称
    @ApiModelProperty("品牌图片地址")
    private String image;//品牌图片地址
    @ApiModelProperty("品牌的首字母")
    private String letter;//品牌的首字母
    @ApiModelProperty("排序")
    private Integer seq;//排序

}