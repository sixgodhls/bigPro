package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.License;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class SecondLevelCategoryVO implements Serializable {
    @ApiModelProperty("二级分类id")
    private Long categoryId;
    @ApiModelProperty("分类级别")
    private Byte categoryLevel;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("父类id")
    private Long parentId;
    @ApiModelProperty("子类列表")
    private List<ThirdLevelCategoryVO> thirdLevelCategoryVOS;
}
