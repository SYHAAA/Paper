package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 公共实体类
 * @author: 沈煜辉
 * @create: 2019-12-05 15:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Common {
    private Date startTime;
    private Date endTime;
}
