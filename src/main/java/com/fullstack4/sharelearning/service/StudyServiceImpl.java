package com.fullstack4.sharelearning.service;

import com.fullstack4.sharelearning.domain.MemberVO;
import com.fullstack4.sharelearning.domain.StudyVO;
import com.fullstack4.sharelearning.dto.*;
import com.fullstack4.sharelearning.mapper.MemberMapper;
import com.fullstack4.sharelearning.mapper.StudyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyServiceIf{
    private final StudyMapper studyMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<StudyDTO> study_info(String user_id) {
        List<StudyDTO> bbsDTOList = studyMapper.study_info(user_id).stream().map(vo -> {
            StudyDTO dto = modelMapper.map(vo, StudyDTO.class);
            // 각 학습에 대한 공유 사용자 정보 가져오기
            List<StudyUserDTO> sharedUsers = studyMapper.findSharedUsers(vo.getNo()).stream().map(userVO -> modelMapper.map(userVO, StudyUserDTO.class)).collect(Collectors.toList());
            dto.setSharedUsers(sharedUsers); // StudyDTO에 공유 사용자 정보 설정
            return dto;
        }).collect(Collectors.toList());

        return bbsDTOList;
    }

    @Override
    public PageResponseDTO<StudyDTO> bbsListByPage(PageRequestDTO pageRequestDTO) {
        List<StudyVO> voList = studyMapper.bbsListByPage(pageRequestDTO);
        List<StudyDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, StudyDTO.class))
                .collect(Collectors.toList());
        int total_count = studyMapper.bbsTotalCount(pageRequestDTO);

        PageResponseDTO<StudyDTO> responseDTO = PageResponseDTO.<StudyDTO>withAll()
                .requestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total_count(total_count)
                .build();
        return responseDTO;
    }



/*    @Override
    public List<StudyDTO> study_info(String user_id, LocalDate date) {
        System.out.println("service user_id : " +user_id );
        List<StudyDTO> bbsDTOList = studyMapper.study_info(user_id,date).stream().map(vo -> {
            StudyDTO dto = modelMapper.map(vo, StudyDTO.class);
            // 각 학습에 대한 공유 사용자 정보 가져오기
            List<StudyUserDTO> sharedUsers = studyMapper.findSharedUsers(vo.getNo()).stream().map(userVO -> modelMapper.map(userVO, StudyUserDTO.class)).collect(Collectors.toList());
            dto.setSharedUsers(sharedUsers); // StudyDTO에 공유 사용자 정보 설정
            return dto;
        }).collect(Collectors.toList());

        return bbsDTOList;
    }*/




}
