package com.syh.dao;

import com.syh.domain.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 分类信息dao接口
 * @author: 沈煜辉
 * @create: 2019-12-10 21:00
 **/
public interface CategoryDao {

    /**
     * 查询所用分类信息的接口
     * @return
     */
    @Select("select \"cid\",\"cname\" from \"tab_category\"")
    List<Category> findAllCategory();

    @Select("select \"cid\",\"cname\" from \"tab_category\" where \"cid\"=#{cid}")
    Category findCategoryByCid(Integer cid);
}
