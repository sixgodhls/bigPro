package com.example.bigpro.Dao;

import com.example.bigpro.entity.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsCategoryMapper {

    @Select("<script>"
            + "SELECT * "
            + "FROM tb_newbee_mall_goods_category "
            + "WHERE parent_id IN "
            + "<foreach item=\"parentId\" collection=\"parentIds\" open=\"(\" separator=\",\" close=\")\"> "
            + "#{parentId} "
            + "</foreach> "
            + "AND category_level = #{categoryLevel} "
            + "AND is_deleted = 0 "
            + "ORDER BY category_rank DESC "
            + "<if test=\"number &gt; 0\"> "
            + "LIMIT #{number} "
            + "</if> "
            + "</script>")
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds,@Param("categoryLevel") int categoryLevel,@Param("number") int number);

}
