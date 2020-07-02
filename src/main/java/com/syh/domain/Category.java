package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 分类字典
 * @author: 沈煜辉
 * @create: 2019-12-10 20:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private Integer cid;
    private String cname;
}
