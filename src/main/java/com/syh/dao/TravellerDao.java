package com.syh.dao;

import com.syh.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 沈煜辉
 * @date 2019/9/27 22:23
 * @describe 游客的dao接口
 */
public interface TravellerDao {

    /**
     * 通过
     * @param id
     * @return
     */
    @Select("select id,name,sex,phoneNum,credentialsType,credentialsNum,travellerType from PAPER_TRAVELLER where id in (select travellerId from PAPER_ORDER_TRAVELLER where orderId=#{id})")
    List<Traveller> findTravellerOrderId(String id);


    /**
     * 保存旅客信息
     * @param traveller
     * @return
     */
    @Insert("insert into PAPER_TRAVELLER(name, sex, phonenum, credentialstype, credentialsnum, travellertype) values (#{name},#{sex},#{phoneNum},#{credentialsType},#{credentialsNum},#{travellerType})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="ID")
    void saveTraveller(Traveller traveller);

    @Select("select ID,name,sex from PAPER_TRAVELLER where name=#{name}")
    Traveller findByName(String name);

    /**
     * 通过id查询旅客信息
     * @param id
     * @return
     */
    @Select("select ID,name,sex from PAPER_TRAVELLER where id=#{id}")
    Traveller findById(String id);

    /**
     * 删除旅客信息
     * @param oid
     * @param tid
     */
    @Delete("delete from PAPER_ORDER_TRAVELLER where orderId=#{oid} and travellerId=#{tid}")
    void deleteTraveller(@Param("oid") String oid,@Param("tid") String tid);
}
