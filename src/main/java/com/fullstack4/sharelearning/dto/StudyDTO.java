package com.fullstack4.sharelearning.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyDTO {
    private int no;
    private String title;
    private String img;
    private String field;
    private String content;
    private String tag;
    private String user_id;
    private int like;
    private LocalDate reg_date;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate exposure_start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate exposure_end;


    // 공유한 사람들의 정보를 담는 리스트를 추가
    private List<StudyUserDTO> sharedUsers;

    //게시글 등록할 때 공유한 사람들 목록
    private String[] share_person;

}
