package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class MallGoodsDetailVO {
    @ApiModelProperty("商品id")
    private Long goodsId;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("商品简介")
    private String goodsIntro;
    @ApiModelProperty("商品图片地址")
    private String goodsCoverImg;
    @ApiModelProperty("商品价格")
    private Integer sellingPrice;
    @ApiModelProperty("商品标签")
    private String tag;
    @ApiModelProperty("商品图片")
    private String[] GoodsCarouseList;
    @ApiModelProperty("商品原价")
    private Integer originalPrice;
    @ApiModelProperty("商品详情字段")
    private String goodsDetailContent;

}
