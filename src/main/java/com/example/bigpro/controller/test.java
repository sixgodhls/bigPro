package com.example.bigpro.controller;

import com.example.bigpro.config.annotation.TokenToMallUser;
import com.example.bigpro.controller.result.Result;
import com.example.bigpro.controller.result.ResultGenerator;
import com.example.bigpro.entity.MallUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation(value = "test",notes = "测试@TokenToMallUser")
@Slf4j
public class test {
    @GetMapping("/testToken")
    public Result<String> testToken(@TokenToMallUser MallUser mallUser){
        log.info(mallUser+"");
        Result result = null;
        if(mallUser == null){
            result = ResultGenerator.genErrorResult(416,"未登录");
            return result;
        }else {
            result = ResultGenerator.genSuccessResult("登录验证通过");

        }
        return result;
    }
}
