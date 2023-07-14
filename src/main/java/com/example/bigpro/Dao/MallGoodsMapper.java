package com.example.bigpro.Dao;

import com.example.bigpro.entity.MallGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallGoodsMapper {
    @Select("<script>"
            + "SELECT *"
            + "FROM tb_newbee_mall_goods_info "
            + "WHERE goods_id IN"
            + "<foreach item=\"goodsIds\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">"
            + "#{goodsIds}"
            + "</foreach>"
            + "ORDER BY field(goods_id,"
            + "<foreach item=\"goodsIds\" collection=\"list\" separator=\",\">"
            + "#{goodsIds}"
            + "</foreach>"
            + ")"
            + "</script>")
    List<MallGoods> findByPrimaryKeys(List<Long> goodsIds);


}
