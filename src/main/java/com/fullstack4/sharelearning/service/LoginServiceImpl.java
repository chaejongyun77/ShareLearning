package com.fullstack4.sharelearning.service;

import com.fullstack4.sharelearning.domain.MemberVO;
import com.fullstack4.sharelearning.dto.LoginDTO;
import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginServiceIf{
    private final LoginMapper loginMapper;
    private final ModelMapper modelMapper;
    @Override
    public MemberDTO login_info(LoginDTO loginDTO) {
        MemberVO memberVO = loginMapper.login_info(loginDTO.getUser_id(),loginDTO.getPwd());
        System.out.println("memberVO : " +memberVO);
        if(memberVO!=null && memberVO.getPwd().equals(loginDTO.getPwd())) {
            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            return memberDTO;
        }
        return null;
    }



}
