package com.example.bigpro.Dao;

import com.example.bigpro.entity.Carousel;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
@Mapper
public interface CarouselMapper {
//    @Results({
//            @Result(id = true,column = "carousel_id",property = "carouselId"),
//            @Result(column = "carousel_url",property = "carouselUrl"),
//            @Result(column = "redirect_url",property = "redirectUrl"),
//            @Result(column = "carousel_rank",property = "carouselRank"),
//            @Result(column = "is_deleted",property = "isDeleted"),
//            @Result(column = "create_time",property = "createTime"),
//            @Result(column = "create_user",property = "createUser"),
//            @Result(column = "update_time",property = "updateTime"),
//            @Result(column = "update_user",property = "updateUser"),
//
//    })
    @Select("select * from tb_newbee_mall_carousel where is_deleted =0 order by carousel_rank desc limit #{Num}")
    List<Carousel> findCarouselsByNum(@Param("Num") int Num);
}
