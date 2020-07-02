package com.syh.service.impl;

import com.syh.dao.CategoryDao;
import com.syh.domain.Category;
import com.syh.service.CategoryService;
import com.syh.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description: 分类信息业务层实现类
 * @author: 沈煜辉
 * @create: 2019-12-10 20:59
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> findAllCategory() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categories = jedis.zrangeByScoreWithScores("categories",1,8);
        List<Category> categoryList = null;
        if(categories==null||categories.size()==0){
            categoryList = categoryDao.findAllCategory();
            for (int i = 0; i < categoryList.size(); i++) {
                jedis.zadd("categories", categoryList.get(i).getCid(), categoryList.get(i).getCname());
            }
        }else{
            categoryList = new ArrayList<>();
            for (Tuple t :categories) {
                Category category = new Category((int) t.getScore(),t.getElement());
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
