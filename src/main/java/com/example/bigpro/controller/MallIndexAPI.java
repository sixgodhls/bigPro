package com.example.bigpro.controller;

import com.example.bigpro.common.Constants;
import com.example.bigpro.common.IndexConfigTypeEnum;
import com.example.bigpro.controller.result.Result;
import com.example.bigpro.controller.result.ResultGenerator;
import com.example.bigpro.entity.VO.IndexCarouselVO;
import com.example.bigpro.entity.VO.IndexConfigGoodsVO;
import com.example.bigpro.entity.VO.IndexInfoVO;
import com.example.bigpro.service.MallCarouselService;
import com.example.bigpro.service.impl.IndexConfigServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "v1",tags = "首页接口")
@RequestMapping("/api/v1")
public class MallIndexAPI {

    @Resource
    private MallCarouselService mallCarouselService;
    @Resource
    private IndexConfigServiceImpl indexConfigService;

    @GetMapping("index-infos")
    @ApiOperation(value = "获取首页数据",notes = "轮播图，新品，推荐")
    public Result<IndexInfoVO> indexInfo(){
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        List<IndexCarouselVO> carouselForIndex = mallCarouselService.getCarouselForIndex(Constants.INDEX_CAROUSEL_NUM);
        List<IndexConfigGoodsVO> hotGoods = indexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_HOTGOODS_NUM);
        List<IndexConfigGoodsVO> newGoods = indexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_NEWGOODS_NUM);
        List<IndexConfigGoodsVO> configGoodsForIndex = indexConfigService.getConfigGoodsForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUM);
        indexInfoVO.setCarousels(carouselForIndex);
        indexInfoVO.setHotGoods(hotGoods);
        indexInfoVO.setNewGoods(newGoods);
        indexInfoVO.setRecommendGoods(configGoodsForIndex);
        return ResultGenerator.genSuccessResult(indexInfoVO);
    }
}
