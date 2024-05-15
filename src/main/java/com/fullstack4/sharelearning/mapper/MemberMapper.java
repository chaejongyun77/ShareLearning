package com.fullstack4.sharelearning.mapper;

import com.fullstack4.sharelearning.domain.MemberVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MemberMapper {

    int modify(@Param("pwd")String pwd, @Param("phoneNumber")String phoneNumber ,@Param("email")String email, @Param("user_id")String user_id );

    List<MemberVO> member_info(String user_id);


}
