package com.fullstack4.sharelearning.service;


import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.dto.PageRequestDTO;
import com.fullstack4.sharelearning.dto.PageResponseDTO;
import com.fullstack4.sharelearning.dto.StudyDTO;

import java.time.LocalDate;
import java.util.List;

public interface StudyServiceIf {
    List<StudyDTO> study_info(String user_id,String reg_date); //수정
/*    List<StudyDTO> study_info(String user_id, LocalDate date);*/

    PageResponseDTO<StudyDTO> bbsListByPage(PageRequestDTO pageRequestDTO);

    int regist(StudyDTO studyDTO);

    StudyDTO view(int no);


    //내가 공유한 학습 페이지
    PageResponseDTO<StudyDTO>  shareListByPage(PageRequestDTO pageRequestDTO,String user_id);


    //내가 공유받은 학습 페이지
    PageResponseDTO<StudyDTO> sharedListByPage(PageRequestDTO pageRequestDTO, String userId);

    int modify(StudyDTO studyDTO);

    int delete_study (int no);

    int insert_like(String user_id, int study_idx);
}
