package com.fullstack4.sharelearning.service;

import com.fullstack4.sharelearning.domain.MemberVO;
import com.fullstack4.sharelearning.dto.LoginDTO;
import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginServiceIf{
    private final LoginMapper loginMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public MemberDTO login_info(LoginDTO loginDTO) {

        int reset_result=0;
        MemberDTO memberDTO = new MemberDTO();

        MemberVO memberVO = loginMapper.login_info(loginDTO.getUser_id(),loginDTO.getPwd());
        System.out.println("memberVO : " +memberVO);
        if(memberVO!=null && memberVO.getPwd().equals(loginDTO.getPwd())) {
            memberDTO = modelMapper.map(memberVO, MemberDTO.class);

        }
        //로그인횟수 초기화
        reset_result = loginMapper.reset_fail(loginDTO.getUser_id());
        System.out.println("로그인 횟수 초기화 :  " + reset_result);
        if(reset_result<=0){
            throw new RuntimeException("RuntimeException for rollback");
        }


        return memberDTO;
    }

    @Override
    public int login_fail(String user_id) {

        int result = loginMapper.login_fail(user_id);

        return result;
    }

    @Override
    public int search_fail(String user_id) {
        int result = loginMapper.search_fail(user_id);
        return result;
    }


}
