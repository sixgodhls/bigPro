package com.example.bigpro.service;

import com.example.bigpro.entity.VO.IndexConfigGoodsVO;

import java.util.List;

public interface IndexConfigService {
    //返回首页配置商品
    List<IndexConfigGoodsVO> getConfigGoodsForIndex(int configType,int num);
}
