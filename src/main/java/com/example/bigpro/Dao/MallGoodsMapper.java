package com.example.bigpro.Dao;

import com.example.bigpro.entity.MallGoods;
import com.example.bigpro.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallGoodsMapper {
    @Select("<script>"
            + "SELECT *"
            + "FROM tb_newbee_mall_goods_info "
            + "WHERE goods_id IN"
            + "<foreach item=\"goodsId\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">"
            + "#{goodsId}"
            + "</foreach>"
            + "ORDER BY field(goods_id,"
            + "<foreach item=\"goodsId\" collection=\"list\" separator=\",\">"
            + "#{goodsId}"
            + "</foreach>"
            + ")"
            + "</script>")
    List<MallGoods> findByPrimaryKeys(List<Long> goodsIds);

    @Select("<script>"
            + "SELECT "
            + "* "
            + "FROM tb_newbee_mall_goods_info "
            + "<where> "
            + "<if test=\"keyword != null and keyword != ''\"> "
            + "AND (goods_name LIKE CONCAT('%', #{keyword}, '%') OR goods_intro LIKE CONCAT('%', #{keyword}, '%')) "
            + "</if> "
            + "<if test=\"goodsCategoryId != null and goodsCategoryId != ''\"> "
            + "AND goods_category_id = #{goodsCategoryId} "
            + "</if>"
            + "<if test=\"goodsSellStatus != null\">"
            + "AND goods_sell_status = #{goodsSellStatus}"
            + "</if>"
            + "</where>"
            + "<if test=\"orderBy != null and orderBy != ''\">"
            + "<choose>"
            + "<when test=\"orderBy == 'new'\">"
            + "ORDER BY goods_id DESC"
            + "</when>"
            + "<when test=\"orderBy == 'price'\">"
            + "ORDER BY selling_price ASC"
            + "</when>"
            + "<otherwise>"
            + "ORDER BY stock_num DESC"
            + "</otherwise>"
            + "</choose>"
            + "</if>"
            + "<if test=\"start != null and limit != null\">"
            + "LIMIT #{start}, #{limit}"
            + "</if>"
            + "</script>")
    List<MallGoods> findMallGoodsListBySearch(PageQueryUtil pageUtil);
    @Select("<script>"
            + "SELECT count(*) "
            + "FROM tb_newbee_mall_goods_info "
            + "<where>"
            + "<if test=\"goodsName != null and goodsName != ''\">"
            + "AND goods_name LIKE CONCAT('%', #{goodsName}, '%')"
            + "</if>"
            + "<if test=\"goodsSellStatus != null and goodsSellStatus != ''\">"
            + "AND goods_sell_status = #{goodsSellStatus}"
            + "</if>"
            + "<if test=\"startTime != null and startTime.trim() != ''\">"
            + "AND create_time &gt; #{startTime}"
            + "</if>"
            + "<if test=\"endTime != null and endTime.trim() != ''\">"
            + "AND create_time &lt; #{endTime}"
            + "</if>"
            + "</where>"
            + "</script>")
    int getTotalMallGoodsBySearch(PageQueryUtil pageUtil);
    @Select("select * from tb_newbee_mall_goods_info where goods_id =#{goodsId}")
    MallGoods selectByPrimaryKey(Long goodsId);
}
