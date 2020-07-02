package com.syh.dao;

import com.syh.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * @description: 产品的dao接口
 * @author: 沈煜辉
 * @create: 2019-12-03 20:20
 **/
public interface ProductDao {

    /**
     * dao 查询所用的产品
     * @return
     */
    @Select("<script>SELECT ID, PRODUCTNUM, PRODUCTNAME, CITYNAME,DEPARTURETIME, PRODUCTPRICE, PRODUCTDESC, PRODUCTSTATUS FROM PAPER_PRODUCT"
            + " <where>"
            + " <if test=\"startTime != null\">"
            + " AND DEPARTURETIME >= #{startTime}"
            + " </if>"
            + " <if test=\"endTime != null\">"
            + " AND #{endTime} >= DEPARTURETIME"
            + " </if>"
            + " <if test=\"cityName != null \">"
            + " AND CITYNAME like '%${cityName}%'"
            + " </if>\n"
            + " <if test=\"productStatus != null \">"
            + " AND PRODUCTSTATUS = #{productStatus}"
            + " </if>\n"
            + " </where> order by DEPARTURETIME DESC"
            + " </script>")
    List<Product> findAllWithPage(Product product);

    /**
     * 保存产品信息
     * @param product
     */
    @Insert("insert into PAPER_PRODUCT(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    /**
     * dao 通过id查询产品信息
     * @param id
     * @return
     */
    @Select("SELECT PRODUCTNUM, PRODUCTNAME, CITYNAME,DEPARTURETIME, PRODUCTPRICE, PRODUCTDESC, PRODUCTSTATUS FROM PAPER_PRODUCT WHERE ID=#{id}")
    Product findProById(String id);

    /**
     * 更新产品信息
     * @param product
     */
    @Update("update PAPER_PRODUCT SET PRODUCTNUM=#{productNum}, PRODUCTNAME=#{productName}, CITYNAME=#{cityName},DEPARTURETIME=#{departureTime}, PRODUCTPRICE=#{productPrice}, PRODUCTDESC=#{productDesc}, PRODUCTSTATUS=#{productStatus} WHERE ID=#{id}")
    void updatePro(Product product);

    /**
     * dao 通过id删除产品信息
     * @param id
     * @return
     */
    @Update("update PAPER_PRODUCT set PRODUCTSTATUS=0 where id=#{id}")
    void deletePro(String id);

    /**
     * dao 批处理删除产品信息
     * @param ids
     */
    @Update(" <script> " +
            " update PAPER_PRODUCT set PRODUCTSTATUS=0 where ID in" +
            " <foreach collection=\"ids\" open=\"(\" close=\")\" item=\"id\" separator=\",\">" +
            " #{id}" +
            " </foreach>" +
            " </script>")
    void deleteManyPro(@Param("ids") String[] ids);
}
