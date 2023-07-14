package com.example.bigpro.config.handler;

import com.example.bigpro.Dao.MallUserMapper;
import com.example.bigpro.Dao.MallUserTokenMapper;
import com.example.bigpro.common.Constants;
import com.example.bigpro.common.MallException;
import com.example.bigpro.common.ServiceResultEnum;
import com.example.bigpro.config.annotation.TokenToMallUser;
import com.example.bigpro.controller.MallPersonalAPI;
import com.example.bigpro.entity.MallUser;
import com.example.bigpro.entity.MallUserToken;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@Slf4j
public class TokenToMallUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private MallUserMapper mallUserMapper;
    @Autowired
    private MallUserTokenMapper mallUserTokenMapper;



    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(TokenToMallUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //感觉可以删if 前面已经判定是不是TokenToMallUser类了这边这个判定多于了 以后写完了再看看
        if(parameter.getParameterAnnotation(TokenToMallUser.class) instanceof TokenToMallUser){
            MallUser mallUser = null;
            String token = webRequest.getHeader("token");
            if(null != token && !"".equals(token) && token.length()== Constants.TOKEN_LENGTH){
                MallUserToken mallUserToken = mallUserTokenMapper.selectByToken(token);
                if(mallUserToken==null || mallUserToken.getExpireTime().getTime() <= System.currentTimeMillis()){
                    MallException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                mallUser = mallUserMapper.selectByPrimaryKey(mallUserToken.getUserId());
                if(mallUser == null){
                    MallException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                if(mallUser.getLockedFlag()==1){
                    MallException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                return mallUser;
            }
        }else {
            MallException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
        }
        return null;
    }

}
