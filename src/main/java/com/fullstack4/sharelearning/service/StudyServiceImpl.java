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

    @Override
    public int regist(StudyDTO studyDTO) {


        int result3= 0;

        //학습테이블 등록
        StudyVO studyVO = modelMapper.map(studyDTO, StudyVO.class);
        int result = studyMapper.regist(studyVO);

        //공유자 테이블 등록
        result3= studyMapper.lastindex();
        studyDTO.setNo(result3);
        String[] shareArray = studyDTO.getShare_person();



        for(int i =0; i<shareArray.length; i++){
            int result2 = studyMapper.share_regist(result3,studyDTO.getUser_id(),shareArray[i]);

        }

        return result;
    }

    @Override
    public StudyDTO view(int no) {
        StudyVO studyVO = studyMapper.view(no);

        List<StudyUserDTO> sharedUsers = studyMapper.findSharedUsers(no).stream().map(userVO -> modelMapper.map(userVO, StudyUserDTO.class)).collect(Collectors.toList());
        StudyDTO studyDTO = modelMapper.map(studyVO,StudyDTO.class);
        String[] sharePersonArray = sharedUsers.stream()
                .map(StudyUserDTO::getShared_by_user_id) // 또는 다른 필드명으로 변경
                .toArray(String[]::new);

        studyDTO.setShare_person(sharePersonArray);
        return studyDTO;
    }

    @Override
    public PageResponseDTO<StudyDTO> shareListByPage(PageRequestDTO pageRequestDTO,String user_id) {
        List<StudyVO> voList = studyMapper.shareListByPage(pageRequestDTO);
        /*String[] shareList = studyMapper.shareList()*/
        List<StudyDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, StudyDTO.class))
                .collect(Collectors.toList());
        int total_count = studyMapper.shareTotalCount(user_id);
        System.out.println("share total_count : " + total_count);

        PageResponseDTO<StudyDTO> responseDTO = PageResponseDTO.<StudyDTO>withAll()
                .requestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total_count(total_count)
                .build();



        System.out.println("comple response " + responseDTO);
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
