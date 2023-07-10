package com.example.bigpro.Dao;

import com.example.bigpro.entity.MallUserToken;

public interface MallUserTokenMapper {
    int insertSelective(MallUserToken record);
    MallUserToken selectByPrimaryKey(Long userId);
    MallUserToken selectByToken(String token);
    int updateByPrimaryKeySelective(MallUserToken record);
}
