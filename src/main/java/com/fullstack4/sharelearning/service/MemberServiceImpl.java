package com.fullstack4.sharelearning.service;

import com.fullstack4.sharelearning.domain.MemberVO;
import com.fullstack4.sharelearning.domain.StudyVO;
import com.fullstack4.sharelearning.dto.*;
import com.fullstack4.sharelearning.mapper.LoginMapper;
import com.fullstack4.sharelearning.mapper.MemberMapper;
import com.fullstack4.sharelearning.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberServiceIf{
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;


    @Override
    public int modify(String pwd, String phoneNumber, String email, String user_id) {
      int result=  memberMapper.modify(pwd, phoneNumber, email, user_id);

          return result;
    }

    @Override
    public List<MemberDTO> member_info(String user_id) {
        List<MemberDTO> memberList = memberMapper.member_info(user_id).stream().map(vo->modelMapper.map(vo,MemberDTO.class)).collect(Collectors.toList());

        return memberList;
    }

    @Override
    public PageResponseDTO<MemberDTO> memberByPage(PageRequestDTO pageRequestDTO) {
        List<MemberVO> voList = memberMapper.memberByPage(pageRequestDTO);
        List<MemberDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());
        int total_count = memberMapper.bbsTotalCount(pageRequestDTO);

        PageResponseDTO<MemberDTO> responseDTO = PageResponseDTO.<MemberDTO>withAll()
                .requestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total_count(total_count)
                .build();

        return responseDTO;
    }
}
