package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class IndexInfoVO {
    @ApiModelProperty("轮播图列表")
    private List<IndexCarouselVO> carousels;
    @ApiModelProperty("首页热销商品（列表）")
    private List<IndexConfigGoodsVO> hotGoods;
    @ApiModelProperty("首页新品（列表）")
    private List<IndexConfigGoodsVO> newGoods;
    @ApiModelProperty("首页推荐商品（列表）")
    private List<IndexConfigGoodsVO> recommendGoods;
}
