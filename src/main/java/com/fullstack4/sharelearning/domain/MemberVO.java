package com.fullstack4.sharelearning.domain;

import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
    private String name;
    private String user_id;
    private String pwd;
    private String phoneNumber;
    private String email;
    private int login_fail;
    private LocalDate last_date;
    private String state;
    private String tmp_state;

}
