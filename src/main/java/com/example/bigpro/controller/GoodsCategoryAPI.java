package com.example.bigpro.controller;

import com.example.bigpro.common.MallException;
import com.example.bigpro.common.ServiceResultEnum;
import com.example.bigpro.controller.result.Result;
import com.example.bigpro.controller.result.ResultGenerator;
import com.example.bigpro.entity.VO.IndexCategoryVO;
import com.example.bigpro.service.CategoryService;
import com.example.bigpro.service.impl.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "v1",tags = "分类接口")
@RequestMapping("/api/v1")
public class GoodsCategoryAPI {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/category")
    @ApiOperation(value = "获取分类数据")
    public Result<List<IndexCategoryVO>> getCategory(){
        List<IndexCategoryVO> categoryForIndex = categoryService.getCategoryForIndex();
        if (CollectionUtils.isEmpty(categoryForIndex)){
            MallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(categoryForIndex);
    }
}
