package com.example.bigpro.service.impl;

import com.example.bigpro.Dao.IndexConfigMapper;
import com.example.bigpro.Dao.MallGoodsMapper;
import com.example.bigpro.entity.IndexConfig;
import com.example.bigpro.entity.MallGoods;
import com.example.bigpro.entity.VO.IndexConfigGoodsVO;
import com.example.bigpro.service.IndexConfigService;
import com.example.bigpro.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
public class IndexConfigServiceImpl implements IndexConfigService {
    @Autowired
    private IndexConfigMapper indexConfigMapper;
    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Override
    public List<IndexConfigGoodsVO> getConfigGoodsForIndex(int configType, int num) {
        List<IndexConfigGoodsVO> indexConfigGoodsVOS = new ArrayList<>(num);
        List<IndexConfig> indexConfigByTypeAndNum = indexConfigMapper.findIndexConfigByTypeAndNum(configType, num);
        log.info(indexConfigByTypeAndNum.toString());
        if(!CollectionUtils.isEmpty(indexConfigByTypeAndNum)){
            List<Long> goodsIds = indexConfigByTypeAndNum.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<MallGoods> byPrimaryKeys = mallGoodsMapper.findByPrimaryKeys(goodsIds);
            indexConfigGoodsVOS = BeanUtil.copyList(byPrimaryKeys,IndexConfigGoodsVO.class);
            for (IndexConfigGoodsVO indexConfigGoodsVO : indexConfigGoodsVOS){
                String goodsName = indexConfigGoodsVO.getGoodsName();
                if (goodsName.length()>30){
                    goodsName=goodsName.substring(0,30)+"...";
                    indexConfigGoodsVO.setGoodsName(goodsName);
                }
            }
        }
        return indexConfigGoodsVOS;

    }
}
