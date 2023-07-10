package com.example.bigpro.Dao;

import com.example.bigpro.entity.MallUser;
import org.apache.ibatis.annotations.Param;

public interface MallUserMapper {
    MallUser selectByLoginNameAndPasswd(@Param("loginName") String loginName,@Param("password") String password);

}
