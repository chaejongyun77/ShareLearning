package com.fullstack4.sharelearning.service;


import com.fullstack4.sharelearning.dto.LoginDTO;
import com.fullstack4.sharelearning.dto.MemberDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberServiceIf {
    int modify(String pwd, String phoneNumber , String email,String user_id);

    List<MemberDTO> member_info(String user_id);
}
