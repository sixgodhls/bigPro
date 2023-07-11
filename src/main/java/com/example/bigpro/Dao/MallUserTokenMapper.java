package com.example.bigpro.Dao;

import com.example.bigpro.entity.MallUserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MallUserTokenMapper {
    int insertSelective(MallUserToken record);
    MallUserToken selectByPrimaryKey(Long userId);
    MallUserToken selectByToken(String token);
    int updateByPrimaryKeySelective(MallUserToken record);
}
