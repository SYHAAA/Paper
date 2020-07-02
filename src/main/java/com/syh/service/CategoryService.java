package com.syh.service;

import com.syh.domain.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: 分类信息业务层接口
 * @author: 沈煜辉
 * @create: 2019-12-10 20:59
 **/
public interface CategoryService {

    /**
     * 业务层查询所用分类信息的接口
     * @return
     */
    List<Category> findAllCategory();
}
