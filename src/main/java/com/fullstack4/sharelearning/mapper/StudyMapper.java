package com.fullstack4.sharelearning.mapper;

import com.fullstack4.sharelearning.domain.MemberVO;
import com.fullstack4.sharelearning.domain.StudyUserVO;
import com.fullstack4.sharelearning.domain.StudyVO;
import com.fullstack4.sharelearning.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;


public interface StudyMapper {

    List<StudyVO> study_info(String user_id);

    /*List<StudyVO> study_info(@Param("user_id") String user_id,@Param("reg_date") LocalDate reg_date);*/

    List<StudyUserVO> findSharedUsers(int study_idx);

    //페이징
    int bbsTotalCount(PageRequestDTO pageRequestDTO);

    List<StudyVO> bbsListByPage(PageRequestDTO pageRequestDTO);

}
