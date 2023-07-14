package com.example.bigpro.service;

import com.example.bigpro.entity.VO.IndexCarouselVO;
import com.example.bigpro.entity.VO.IndexConfigGoodsVO;

import java.util.List;

public interface MallCarouselService {
    //返回首页固定数量的轮播图商品对象
    List<IndexCarouselVO> getCarouselForIndex(int num);
}
