package com.fullstack4.sharelearning.domain;

import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyUserVO {
    private int no;
    private int study_idx;
    private String user_id;
    private String shared_by_user_id;
}
