package com.example.bigpro.controller;

import com.example.bigpro.common.Constants;
import com.example.bigpro.config.annotation.TokenToMallUser;
import com.example.bigpro.controller.result.Result;
import com.example.bigpro.controller.result.ResultGenerator;
import com.example.bigpro.entity.MallUser;
import com.example.bigpro.entity.MallUserV0;
import com.example.bigpro.param.MallUserLoginParam;
import com.example.bigpro.param.MallUserUpdateParam;
import com.example.bigpro.service.MallUserService;
import com.example.bigpro.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;

@RestController
@Api(value = "v1",tags = "用户操作接口")
@RequestMapping("/api/v1")
public class MallPersonalAPI {
    @Resource
    private MallUserService mallUserService;

    private static final Logger logger = LoggerFactory.getLogger(MallPersonalAPI.class);

    @PostMapping("/user/login")
    @ApiOperation(value = "登录接口",notes = "返回token")
    public Result<String> login(@RequestBody @Valid MallUserLoginParam mallUserLoginParam){
        String loginResult = mallUserService.login(mallUserLoginParam.getLoginName(), mallUserLoginParam.getPasswordMD5());
        logger.info("login api,loginName={},loginResult={}",mallUserLoginParam.getLoginName(),loginResult);
        //登录成功
        if(!StringUtils.isEmpty(loginResult)&&loginResult.length()== Constants.TOKEN_LENGTH){
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息" ,notes = " ")
    public Result<MallUserV0> getUserDetail(@TokenToMallUser MallUser mallUser){
        MallUserV0 mallUserV0 = new MallUserV0();
        BeanUtil.copyProperties(mallUser,mallUserV0);
        return ResultGenerator.genSuccessResult(mallUserV0);
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "修改用户信息",notes = " ")
    public Result updateUserInfo(@RequestBody @ApiParam("用户信息") MallUserUpdateParam mallUserUpdateParam,@TokenToMallUser MallUser mallUser){
        Boolean info = mallUserService.updateUserInfo(mallUserUpdateParam, mallUser.getUserId());
        if(info){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping("/user/logout")
    @ApiOperation(value = "登出接口",notes = " ")
    public Result<String> logout(@TokenToMallUser MallUser mallUser){
        Boolean logout = mallUserService.logout(mallUser.getUserId());
        if (logout){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("logout error");
    }
}
