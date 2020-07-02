package com.syh.service;

import com.syh.domain.Product;

import java.util.List;

/**
 * @description: 产品的业务层接口
 * @author: 沈煜辉
 * @create: 2019-12-03 20:19
 **/

public interface ProductService {
    /**
     * 查询所用的产品
     * @return
     */
    List<Product> findAllWithPage(Product product,Integer pageNum,Integer pageSize);

    /**
     * 保存产品信息
     * @param product
     */
    void saveProduct(Product product);

    /**
     * 通过id查询产品信息
     * @param id 产品id
     * @return
     */
    Product findProById(String id);

    /**
     * service更新产品信息
     * @param product
     */
    void updatePro(Product product);

    /**
     * service 通过id删除产品
     * @param id
     * @return
     */
    void deletePro(String id);

    /**
     * 批处理删除产品
     * @param ids
     */
    void deleteManyPro(String[] ids);
}
