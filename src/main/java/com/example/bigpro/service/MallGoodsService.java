package com.example.bigpro.service;

import com.example.bigpro.util.PageQueryUtil;
import com.example.bigpro.util.PageResult;

public interface MallGoodsService {
    PageResult searchByMallGoods(PageQueryUtil pageUtil);
}
