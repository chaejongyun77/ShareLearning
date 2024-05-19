package com.fullstack4.sharelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDTO {
    private int no;
    private String user_id;
    private int study_idx;
    private int like_count;

}
