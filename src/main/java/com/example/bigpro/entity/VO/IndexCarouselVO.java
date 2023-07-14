package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class IndexCarouselVO {
    @ApiModelProperty("图片地址")
    private String carouselUrl;
    @ApiModelProperty("跳转路径")
    private String redirectUrl;
}
