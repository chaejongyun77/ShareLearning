package com.fullstack4.sharelearning.service;


import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.dto.PageRequestDTO;
import com.fullstack4.sharelearning.dto.PageResponseDTO;
import com.fullstack4.sharelearning.dto.StudyDTO;

import java.time.LocalDate;
import java.util.List;

public interface StudyServiceIf {
    List<StudyDTO> study_info(String user_id);
/*    List<StudyDTO> study_info(String user_id, LocalDate date);*/

    PageResponseDTO<StudyDTO> bbsListByPage(PageRequestDTO pageRequestDTO);

}
