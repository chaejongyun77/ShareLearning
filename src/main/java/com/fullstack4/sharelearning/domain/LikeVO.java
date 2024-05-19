package com.fullstack4.sharelearning.domain;

import com.fullstack4.sharelearning.dto.StudyUserDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeVO {
    private int no;
    private String user_id;
    private int study_idx;
    private int like_count;



}
