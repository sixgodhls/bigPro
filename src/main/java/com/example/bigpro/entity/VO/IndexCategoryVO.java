package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class IndexCategoryVO implements Serializable {
    @ApiModelProperty("一级分类id")
    private Long categoryId;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("分类级别")
    private Byte categoryLevel;
    @ApiModelProperty("子类列表")
    private List<SecondLevelCategoryVO> secondLevelCategoryVOS;
}
