package com.fullstack4.sharelearning.service;

import com.fullstack4.sharelearning.domain.MemberVO;
import com.fullstack4.sharelearning.dto.LoginDTO;
import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.mapper.LoginMapper;
import com.fullstack4.sharelearning.util.CommonUtil;
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
        int tmp_result=0;
        MemberDTO memberDTO = new MemberDTO();


        MemberVO memberVO = loginMapper.login_info(loginDTO.getUser_id(),loginDTO.getPwd());


        if(memberVO!=null && memberVO.getPwd().equals(loginDTO.getPwd())) {
            

            memberDTO = modelMapper.map(memberVO, MemberDTO.class);


            //로그인횟수 초기화
            reset_result = loginMapper.reset_fail(loginDTO.getUser_id());

            if (reset_result <= 0) {
                throw new RuntimeException("RuntimeException for rollback");
            }
            //임시비밀번호 상태 N으로 변경
            tmp_result = loginMapper.update_tmp(loginDTO.getUser_id());
            if (tmp_result <= 0) {
                throw new RuntimeException("RuntimeException for rollback");
            }

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

         MemberVO memberVO = loginMapper.search_fail(user_id);

         MemberDTO memberDTO = new MemberDTO();

            memberDTO.setLogin_fail(memberVO.getLogin_fail());


        return memberDTO.getLogin_fail();
    }

    @Override
    public MemberDTO find_pwd(String user_id) {

        int update_pwd =0;

        MemberVO memberVO = loginMapper.find_pwd(user_id);
        if(memberVO !=null){
            MemberDTO memberDTO = new MemberDTO();
           memberDTO = modelMapper.map(memberVO, MemberDTO.class);

           // 임시 비밀번호 생성 후 삽입
            String tmp_pwd = CommonUtil.generate();

           update_pwd = loginMapper.new_pwd(user_id,tmp_pwd);
           memberDTO.setPwd(tmp_pwd);
           if(update_pwd <0){
               throw new RuntimeException("RuntimeException for rollback");
           }
            return memberDTO;
        }
        else{
            MemberDTO memberDTO =null;
            return memberDTO;
        }

    }

    @Override
    public int update_pwd(String user_id, String pwd) {
        int update_result = loginMapper.update_pwd(user_id,pwd);
        return update_result;

    }


}
