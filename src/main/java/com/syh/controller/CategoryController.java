package com.syh.controller;

import com.syh.domain.Category;
import com.syh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 分类信息controller
 * @author: 沈煜辉
 * @create: 2019-12-10 20:57
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findAll.do")
    List<Category> findAllCategory(){
        return categoryService.findAllCategory();
    }
}
