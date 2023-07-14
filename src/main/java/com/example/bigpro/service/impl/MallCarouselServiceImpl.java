package com.example.bigpro.service.impl;

import com.example.bigpro.Dao.CarouselMapper;
import com.example.bigpro.entity.Carousel;
import com.example.bigpro.entity.VO.IndexCarouselVO;
import com.example.bigpro.entity.VO.IndexConfigGoodsVO;
import com.example.bigpro.service.MallCarouselService;
import com.example.bigpro.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallCarouselServiceImpl implements MallCarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<IndexCarouselVO> getCarouselForIndex(int num) {
        List<IndexCarouselVO> indexCarouselVOS = new ArrayList<>(num);
        List<Carousel> carouselsByNum = carouselMapper.findCarouselsByNum(num);
        if(!CollectionUtils.isEmpty(carouselsByNum)){
            indexCarouselVOS = BeanUtil.copyList(carouselsByNum,IndexCarouselVO.class);
        }
        return indexCarouselVOS;
    }
}
