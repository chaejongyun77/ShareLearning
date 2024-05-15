package com.fullstack4.sharelearning.domain;

import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyVO {
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
    private LocalDate exposure_start;
    private LocalDate exposure_end;
}
