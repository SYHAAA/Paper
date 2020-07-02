package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 沈煜辉
 * @date 2019/9/27 14:39
 * @describe 旅客的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traveller implements Serializable {
    private String id;
    private String oid;
    private String name;
    private String sex;
    private String phoneNum;
    private Integer credentialsType;
    private String credentialsNum;
    private Integer travellerType;
}
