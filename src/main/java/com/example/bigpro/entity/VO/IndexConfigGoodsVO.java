package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IndexConfigGoodsVO {
    @ApiModelProperty("商品id")
    private Long goodsId;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("商品图片地址")
    private String goodsCoverImg;
    @ApiModelProperty("商品价格")
    private Integer sellingPrice;
}
