package com.example.bigpro.service.impl;

import com.example.bigpro.Dao.MallUserMapper;
import com.example.bigpro.Dao.MallUserTokenMapper;
import com.example.bigpro.common.ServiceResultEnum;
import com.example.bigpro.entity.MallUserToken;
import com.example.bigpro.entity.MallUser;
import com.example.bigpro.service.MallUserService;
import com.example.bigpro.util.NumberUtil;
import com.example.bigpro.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MallUserServiceImpl implements MallUserService {
    @Autowired
    public MallUserMapper mallUserMapper;
    @Autowired
    public MallUserTokenMapper mallUserTokenMapper;
    //生成token值
    public String getNewToken(String timeStr,Long userId){
        String src = timeStr+userId+ NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    public String login(String loginName,String passwordMD5){
        MallUser mallUser = mallUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (mallUser != null){
            if (mallUser.getLockedFlag() == 1){
                return ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult();
            }
            String token = getNewToken(System.currentTimeMillis() + "", mallUser.getUserId());
            MallUserToken mallUserToken = mallUserTokenMapper.selectByPrimaryKey(mallUser.getUserId());
            Date now = new Date();
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);
            if(mallUserToken == null){
                mallUserToken.setToken(token);
                mallUserToken.setUserId(mallUser.getUserId());
                mallUserToken.setExpireTime(expireTime);
                mallUserToken.setUpdateTime(now);
                if(mallUserTokenMapper.insertSelective(mallUserToken)>0){
                    return token;
                }
            }else {
                mallUserToken.setToken(token);
                mallUserToken.setExpireTime(expireTime);
                mallUserToken.setUpdateTime(now);
                if(mallUserTokenMapper.updateByPrimaryKeySelective(mallUserToken)>0){
                    return token;
                }
            }

        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

}
