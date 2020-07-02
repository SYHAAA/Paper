package com.syh.controller;

import com.github.pagehelper.PageInfo;
import com.syh.domain.Product;
import com.syh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 产品的controller
 * @author: 沈煜辉
 * @create: 2019-12-03 20:15
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/findAllWithPage.do")
    public PageInfo<Product> findAllWithPage(Product product, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        List<Product> list = productService.findAllWithPage(product,pageNum,pageSize);
        PageInfo<Product> productPageInfo = new PageInfo<>(list);
        return productPageInfo;
    }

    @RequestMapping("/saveProduct.do")
    public void saveProduct(Product product){
        productService.saveProduct(product);
    }

    @RequestMapping("/findProById.do")
    public Product findOne(String id){
         Product product = productService.findProById(id);
         return product;
    }

    @RequestMapping("/updateProduct.do")
    public void updatePro(Product product){
        productService.updatePro(product);
    }

    @RequestMapping("/deletePro.do")
    public void deletePro(String id){
        productService.deletePro(id);
    }

    @RequestMapping("/deleteManyPro.do")
    public void deleteManyPro(String[] ids){
        productService.deleteManyPro(ids);
    }

}
