package com.example.bigpro.entity.VO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
@Data
public class ThirdLevelCategoryVO implements Serializable {
    @ApiModelProperty("三级分类id")
    private Long categoryId;
    @ApiModelProperty("分类级别")
    private Byte categoryLevel;
    @ApiModelProperty("分类名称")
    private String categoryName;
}
