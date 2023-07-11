package com.example.bigpro.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class MallUserLoginParam {
    @ApiModelProperty("登录名")
    @NotEmpty(message = "登录名不能为空")
    private String loginName;

    @ApiModelProperty("密码（MD5加密）")
    @NotEmpty(message = "密码不能为空")
    private String passwordMD5;
}
