package com.example.bigpro.service.impl;

import com.example.bigpro.Dao.MallGoodsMapper;
import com.example.bigpro.entity.MallGoods;
import com.example.bigpro.entity.VO.SearchGoodsVO;
import com.example.bigpro.service.MallGoodsService;
import com.example.bigpro.util.BeanUtil;
import com.example.bigpro.util.PageQueryUtil;
import com.example.bigpro.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallGoodsServiceImpl implements MallGoodsService {
    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Override
    public PageResult searchByMallGoods(PageQueryUtil pageUtil) {
        List<MallGoods> mallGoodsListBySearch = mallGoodsMapper.findMallGoodsListBySearch(pageUtil);
        int totalMallGoodsBySearch = mallGoodsMapper.getTotalMallGoodsBySearch(pageUtil);
        List<SearchGoodsVO> searchGoodsVOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(mallGoodsListBySearch)){
            searchGoodsVOS = BeanUtil.copyList(mallGoodsListBySearch, SearchGoodsVO.class);
            for(SearchGoodsVO searchGoodsVO:searchGoodsVOS){
                String goodsName=searchGoodsVO.getGoodsName();
                String goodsIntro=searchGoodsVO.getGoodsIntro();
                if(goodsName.length()>28){
                    goodsName=goodsName.substring(0,28)+"...";
                    searchGoodsVO.setGoodsName(goodsName);
                }
                if(goodsIntro.length()>28){
                    goodsIntro=goodsIntro.substring(0,28)+"...";
                    searchGoodsVO.setGoodsIntro(goodsIntro);
                }

            }
        }
        PageResult pageResult1 = new PageResult(searchGoodsVOS, totalMallGoodsBySearch, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult1;
    }

    public MallGoods getMallGoodsById(Long goodsId){
        return mallGoodsMapper.selectByPrimaryKey(goodsId);
    }
}
