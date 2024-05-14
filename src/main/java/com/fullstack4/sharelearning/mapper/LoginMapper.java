package com.fullstack4.sharelearning.mapper;

import com.fullstack4.sharelearning.domain.MemberVO;
import org.apache.ibatis.annotations.Param;


public interface LoginMapper {
    MemberVO login_info(@Param("user_id") String user_id, @Param("pwd") String pwd);

    int login_fail(String user_id);

    int reset_fail(String user_id);

    MemberVO search_fail(String user_id);

    MemberVO find_pwd(String user_id);

    int new_pwd(@Param("user_id") String user_id, @Param("pwd") String pwd);

    int update_tmp(String user_id);

    int update_pwd(@Param("user_id") String user_id, @Param("pwd") String pwd);

}
