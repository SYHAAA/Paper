package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 用户·收藏路线实体
 * @author: 沈煜辉
 * @create: 2019-12-13 16:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavorite {
    private Route route;
    private Date date;
    private UserInfo userInfo;
}
