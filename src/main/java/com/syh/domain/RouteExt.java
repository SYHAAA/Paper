package com.syh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 路线扩展类
 * @author: 沈煜辉
 * @create: 2020-02-10 15:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
public class RouteExt {
    Date startTime;
    Date endTime;
    Double priceLow;
    Double priceHigh;
}
