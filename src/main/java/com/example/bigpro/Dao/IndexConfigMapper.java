package com.example.bigpro.Dao;

import com.example.bigpro.entity.IndexConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IndexConfigMapper {
    @Select("select * from tb_newbee_mall_index_config where config_type = #{configType} and is_deleted = 0" +
            " order by config_rank desc limit #{num}")
    List<IndexConfig> findIndexConfigByTypeAndNum(@Param("configType") int configType,@Param("num") int num);
}
