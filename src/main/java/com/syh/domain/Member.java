package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 沈煜辉
 * @date 2019/9/27 14:37
 * @describe 会员实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable {
    private String id;
    private String name;
    private String nickName;
    private String phoneNum;
    private String email;
}
