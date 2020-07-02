package com.syh.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 产品实体类
 * @author: 沈煜辉
 * @create: 2019-12-03 20:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Common implements Serializable {
    private String id;
    private String productNum;
    private String productName;
    private String cityName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date departureTime;
    private Double productPrice;
    private String productDesc;
    private String productStatus;

    public void setCityName(String cityName) {
        if(cityName == ""){
            this.cityName = null;
        }else{
            this.cityName = cityName;
        }
    }

    public void setProductStatus(String productStatus) {
        if(productStatus == ""){
            this.productStatus = null;
        }else{
            this.productStatus = productStatus;
        }
    }
}
