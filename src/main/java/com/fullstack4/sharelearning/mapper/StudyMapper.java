package com.fullstack4.sharelearning.mapper;

import com.fullstack4.sharelearning.domain.MemberVO;
import com.fullstack4.sharelearning.domain.StudyUserVO;
import com.fullstack4.sharelearning.domain.StudyVO;
import com.fullstack4.sharelearning.dto.PageRequestDTO;
import com.fullstack4.sharelearning.dto.StudyDTO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;


public interface StudyMapper {

    List<StudyVO> study_info(@Param("user_id") String user_id,@Param("reg_date") String reg_date);

    /*List<StudyVO> study_info(@Param("user_id") String user_id,@Param("reg_date") LocalDate reg_date);*/

    List<StudyUserVO> findSharedUsers(int study_idx);

    //페이징
    int bbsTotalCount(PageRequestDTO pageRequestDTO);

    List<StudyVO> bbsListByPage(PageRequestDTO pageRequestDTO);

    int regist(StudyVO studyVO);
    int lastindex();

    int share_regist(@Param("study_idx")int study_idx,@Param("user_id")String user_id,@Param("shared_by_user_id") String shared_by_user_id);

    StudyVO view(int no);

    //내가 공유한 학습 페이지
    List<StudyVO> shareListByPage(PageRequestDTO pageRequestDTO);

    int shareTotalCount(String user_id);

    //내가 공유한 학습 -> 공유사람들
    String[] shareList(@Param("study_idx")int study_idx,@Param("user_id")String user_id);



    //내가 공유 받은 학습
    List<StudyVO> sharedListByPage(@Param("pageRequestDTO")PageRequestDTO pageRequestDTO,@Param("user_id") String user_id);

    int getSharedStudyTotalCount(String user_id);

    int modify(StudyVO studyVO);


    int delete_share(int no);

    int delete_study(int no);

    //좋아요 기능
    int insert_like(@Param("user_id")String user_id, @Param("study_idx")int study_idx);

    int update_like (int study_idx);

    int select_like (@Param("user_id")String user_id, @Param("study_idx")int study_idx);
}
