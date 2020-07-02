package com.syh.service.impl;

import com.github.pagehelper.PageHelper;
import com.syh.dao.ProductDao;
import com.syh.domain.Product;
import com.syh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @description: 产品的业务层实现类
 * @author: 沈煜辉
 * @create: 2019-12-03 20:19
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAllWithPage(Product product,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return productDao.findAllWithPage(product);
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public Product findProById(String id) {
        return productDao.findProById(id);
    }

    @Override
    public void updatePro(Product product) {
        productDao.updatePro(product);
    }

    @Override
    public void deletePro(String id) {
        productDao.deletePro(id);
    }

    @Override
    public void deleteManyPro(String[] ids) {
        productDao.deleteManyPro(ids);
    }
}
