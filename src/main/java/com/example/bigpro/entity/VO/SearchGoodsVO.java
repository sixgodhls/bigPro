package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class SearchGoodsVO {
    @ApiModelProperty("商品id")
    private Long goodsId;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("商品简介")
    private String goodsIntro;
    @ApiModelProperty("商品图片")
    private String goodsCoverImg;
    @ApiModelProperty("商品价格")
    private Integer sellingPrice;
}
