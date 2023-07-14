package com.example.bigpro.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MallUserVO {
    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户登录名")
    private String loginName;

    @ApiModelProperty("个性签名")
    private String introduceSign;
}
