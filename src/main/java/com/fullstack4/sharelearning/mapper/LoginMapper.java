package com.fullstack4.sharelearning.mapper;

import com.fullstack4.sharelearning.domain.MemberVO;
import org.apache.ibatis.annotations.Param;


public interface LoginMapper {
    MemberVO login_info(@Param("user_id") String user_id, @Param("pwd") String pwd);

}
