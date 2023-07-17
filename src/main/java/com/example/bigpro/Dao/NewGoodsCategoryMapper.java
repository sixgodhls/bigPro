package com.example.bigpro.Dao;

import com.example.bigpro.entity.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewGoodsCategoryMapper {

    List<GoodsCategory> findAll();

    List<GoodsCategory> findGoodsByParentId(@Param("PID") Long id);

}
