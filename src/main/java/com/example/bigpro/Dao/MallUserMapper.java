package com.example.bigpro.Dao;

import com.example.bigpro.entity.MallUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MallUserMapper {

    MallUser selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    MallUser selectByPrimaryKey(@Param("userId") Long userId);

    int updateByPrimaryKeySelective(MallUser record);

    int updateByPrimaryKey(MallUser record);


}