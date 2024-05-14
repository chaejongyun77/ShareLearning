package com.fullstack4.sharelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

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
