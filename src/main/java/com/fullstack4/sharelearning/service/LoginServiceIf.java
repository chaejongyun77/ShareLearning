package com.fullstack4.sharelearning.service;


import com.fullstack4.sharelearning.dto.LoginDTO;
import com.fullstack4.sharelearning.dto.MemberDTO;

public interface LoginServiceIf {
    MemberDTO login_info(LoginDTO loginDTO);
}
