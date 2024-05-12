package com.fullstack4.sharelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
