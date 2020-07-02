package com.syh.dao;

import com.syh.domain.Seller;
import org.apache.ibatis.annotations.Select;

/**
 * @description: 商家dao
 * @author: 沈煜辉
 * @create: 2019-12-12 19:30
 **/
public interface SellerDao {

    /**
     * 通过sid查询商家信息
     * @param sid
     * @return
     */
    @Select("select  \"sid\", \"sname\", \"consphone\", \"address\" from \"tab_seller\" where \"sid\"=#{sid}")
    Seller findSellerBySid(Integer sid);
}
