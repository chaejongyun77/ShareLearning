package com.fullstack4.sharelearning.service;


import com.fullstack4.sharelearning.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberServiceIf {
    int modify(String pwd, String phoneNumber , String email,String user_id);

    List<MemberDTO> member_info(String user_id);

    //검색 회원 아이디 목록
    PageResponseDTO<MemberDTO> memberByPage(PageRequestDTO pageRequestDTO);

}

