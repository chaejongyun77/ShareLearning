package com.fullstack4.sharelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

@Log4j2
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @PositiveOrZero
    @Min(value=0)
    private int total_count=0;
    @Builder.Default
    @PositiveOrZero
    @Min(value=1)
    private int first_page=1;
    @Builder.Default
    @PositiveOrZero
    @Min(value=1)
    private int page=1;
    @Builder.Default
    @PositiveOrZero
    @Min(value=1)
    private int page_size=10;
    @Builder.Default
    @PositiveOrZero
    @Min(value=1)
    private int total_page=1;
    @Builder.Default
    @PositiveOrZero
    @Min(value=0)
    private int page_skip_count=0;
    @Builder.Default
    @PositiveOrZero
    @Min(value=1)
    private int page_block_size=10;
    @Builder.Default
    @PositiveOrZero
    @Min(value=1)
    private int page_block_start=1;
    @Builder.Default
    @PositiveOrZero
    @Min(value=1)
    private int page_block_end=1;

    private String[] search_type;
    private String search_word;
    private String search_date1;
    private String search_date2;
    private String cate;
    private String cate1;
    private String cate2;
    private String cate3;
    private String order;
    private String user_id;

    //게시글 등록할 때 공유한 사람들 목록
    private String[] share_person;


    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public int getPage_skip_count() {
        return (this.page-1)*this.page_size;
    }
}
