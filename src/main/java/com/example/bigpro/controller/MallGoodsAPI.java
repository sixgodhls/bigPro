package com.example.bigpro.controller;

import com.example.bigpro.common.MallException;
import com.example.bigpro.common.ServiceResultEnum;
import com.example.bigpro.config.annotation.TokenToMallUser;
import com.example.bigpro.controller.result.Result;
import com.example.bigpro.controller.result.ResultGenerator;
import com.example.bigpro.entity.MallGoods;
import com.example.bigpro.entity.MallUser;
import com.example.bigpro.entity.VO.MallGoodsDetailVO;
import com.example.bigpro.entity.VO.SearchGoodsVO;
import com.example.bigpro.service.impl.MallGoodsServiceImpl;
import com.example.bigpro.util.BeanUtil;
import com.example.bigpro.util.PageQueryUtil;
import com.example.bigpro.util.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "v1",tags = "商品接口")
@RequestMapping("/api/v1")
public class MallGoodsAPI {
    @Autowired
    private MallGoodsServiceImpl mallGoodsService;

    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口",notes = "根据关键字和分类id搜索")
    public Result<PageResult<List<SearchGoodsVO>>> search(@RequestParam(required = false) @ApiParam(value = "搜索关键字") String keyword,
                                                          @RequestParam(required = false) @ApiParam(value = "分类id") Long goodsCategoryId,
                                                          @RequestParam(required = false) @ApiParam(value = "orderBy") String orderBy,
                                                          @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
                                                          @TokenToMallUser MallUser mallUser
                                                          ){

        Map params = new HashMap<>(4);
        if(goodsCategoryId == null && StringUtils.isEmpty(keyword)){
            MallException.fail("非法参数");
        }
        if (pageNumber==null||pageNumber<1){
            pageNumber=1;
        }
        params.put("goodsCategoryId",goodsCategoryId);
        params.put("page",pageNumber);
        params.put("limit",10);
        if(!StringUtils.isEmpty(keyword)){
            params.put("keyword",keyword);
        }
        if (!StringUtils.isEmpty(orderBy)){
            params.put("order",orderBy);
        }
        params.put("goodsSellStatus",0);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(mallGoodsService.searchByMallGoods(pageQueryUtil));
    }

    @GetMapping("/goods/detail/{goodsId}")
    @ApiOperation(value = "商品详情接口",notes = "传参为商品id")
    private Result<MallGoodsDetailVO> goodsDetail(@PathVariable("goodsId") Long goodsId, @TokenToMallUser MallUser mallUser){
        if(goodsId<1){
            return ResultGenerator.genFailResult("参数异常");
    }
        MallGoods mallGoodsById = mallGoodsService.getMallGoodsById(goodsId);
        if(mallGoodsById==null){
            return ResultGenerator.genFailResult("参数异常");
        }
        if (0!=mallGoodsById.getGoodsSellStatus()){
            MallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        MallGoodsDetailVO mallGoodsDetailVO = new MallGoodsDetailVO();
        BeanUtil.copyProperties(mallGoodsById,mallGoodsDetailVO);
        mallGoodsDetailVO.setGoodsCarouseList(mallGoodsById.getGoodsCarousel().split(","));
        return ResultGenerator.genSuccessResult(mallGoodsDetailVO);
    }
    }
