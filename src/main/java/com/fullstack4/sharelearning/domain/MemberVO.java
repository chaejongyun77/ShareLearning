package com.fullstack4.sharelearning.domain;

import lombok.*;

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

}
