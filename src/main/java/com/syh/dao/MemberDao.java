package com.syh.dao;

import com.syh.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 沈煜辉
 * @date 2019/9/27 22:23
 * @describe 会员的dao接口
 */
public interface MemberDao {
    /**
     * 通过id查询会员信息
     * @param id 会员id
     * @return 会员实体
     */
    @Select("select id,name,nickName,phoneNum,email from PAPER_MEMBER where id=#{id}")
    Member findMemById(String id);
}
