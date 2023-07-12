package com.example.bigpro.service;

import com.example.bigpro.param.MallUserUpdateParam;

public interface MallUserService {
    String login(String loginName,String passwordMD5);

    Boolean updateUserInfo(MallUserUpdateParam mallUser,Long userId);

    Boolean logout(Long userId);
}
