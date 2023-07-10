package com.example.bigpro.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MallUserToken {
    private Long userId;
    private String Token;
    private Date updateTime;
    private Date expireTime;
}
