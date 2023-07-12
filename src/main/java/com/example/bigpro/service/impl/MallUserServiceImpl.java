package com.example.bigpro.service.impl;

import com.example.bigpro.Dao.MallUserMapper;
import com.example.bigpro.Dao.MallUserTokenMapper;
import com.example.bigpro.common.MallException;
import com.example.bigpro.common.ServiceResultEnum;
import com.example.bigpro.entity.MallUserToken;
import com.example.bigpro.entity.MallUser;
import com.example.bigpro.param.MallUserUpdateParam;
import com.example.bigpro.service.MallUserService;
import com.example.bigpro.util.MD5Util;
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
                MallUserToken mallUserTokenNew = new MallUserToken();
                mallUserTokenNew.setToken(token);
                mallUserTokenNew.setUserId(mallUser.getUserId());
                mallUserTokenNew.setExpireTime(expireTime);
                mallUserTokenNew.setUpdateTime(now);
                if(mallUserTokenMapper.insertSelective(mallUserTokenNew)>0){
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

    @Override
    public Boolean updateUserInfo(MallUserUpdateParam mallUser, Long userId) {
        MallUser user = mallUserMapper.selectByPrimaryKey(userId);
        if (user == null) {
            MallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        user.setNickName(mallUser.getNickName());
        if (!mallUser.getPasswordMd5().equals(MD5Util.MD5Encode("","UTF-8"))){
            user.setPasswordMD5(mallUser.getPasswordMd5());
        }
        user.setIntroduceSign(mallUser.getIntroduceSign());
        if (mallUserMapper.updateByPrimaryKeySelective(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean logout(Long userId) {
        return mallUserTokenMapper.deleteByPrimaryKey(userId)>0;
    }
}
