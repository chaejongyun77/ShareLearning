package com.fullstack4.sharelearning.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Data
public class PageResponseDTO<E> {
    private int total_count;
    private int page;
    private int page_size;
    private int page_skip_count;
    private int total_page;
    private int page_block_size;
    private int page_block_start;
    private int page_block_end;

    private boolean prev_page_flag;
    private boolean next_page_flag;

    private String[] search_type;
    private String search_word;
    private String search_date1;
    private String search_date2;
    private String cate;
    private String cate1;
    private String cate2;
    private String cate3;
    private String order;

    List<E> dtoList;

    private String linkParams;

    PageResponseDTO() {}

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO requestDTO, List<E> dtoList, int total_count) {
        log.info("========================================");
        log.info("PageResponseDTO START");
        this.total_count = total_count;
        this.page = requestDTO.getPage();
        this.page_size = requestDTO.getPage_size();
        this.page_skip_count = (this.page-1)*this.page_size;
        this.total_page = (this.total_count > 0 ?
                (int)Math.ceil(this.total_count/(double)this.page_size) : 1);
        this.page_block_size = requestDTO.getPage_block_size();
        //this.page_block_start = requestDTO.getPage_block_start();
        //this.page_block_end = requestDTO.getPage_block_end();
        this.setPage_block_start();
        this.setPage_block_end();
        this.prev_page_flag = (this.page_block_start > 1);
        this.next_page_flag = (this.total_page > this.page_block_end);
        this.dtoList = dtoList;

        search_type = requestDTO.getSearch_type()!=null?requestDTO.getSearch_type():null;
        search_word = requestDTO.getSearch_word()!=null?requestDTO.getSearch_word():null;
        search_date1 = requestDTO.getSearch_date1()!=null?requestDTO.getSearch_date1():null;
        search_date2 = requestDTO.getSearch_date2()!=null?requestDTO.getSearch_date2():null;
        cate = requestDTO.getCate()!=null?requestDTO.getCate():null;
        cate1 = requestDTO.getCate1()!=null?requestDTO.getCate1():null;
        cate2 = requestDTO.getCate2()!=null?requestDTO.getCate2():null;
        cate3 = requestDTO.getCate3()!=null?requestDTO.getCate3():null;
        order = requestDTO.getOrder()!=null?requestDTO.getOrder():null;


        StringBuilder search_type_String = new StringBuilder();
        if(search_type!=null) {
            for (String i : search_type) {
                search_type_String.append("&search_type=" + i);
            }
        }
        if(search_word!=null) {
           search_type_String.append("&search_word=" + search_word);
        }
        if(search_date1!=null) {
            search_type_String.append("&search_date1=" + search_date1);
        }
        if(search_date2!=null) {
            search_type_String.append("&search_date2=" + search_date2);
        }
        if(cate != null) {
            search_type_String.append("&cate=" + cate);
        }
        if(cate1 != null) {
            search_type_String.append("&cate1=" + cate1);
        }
        if(cate2 != null) {
            search_type_String.append("&cate2=" + cate2);
        }
        if(cate3 != null) {
            search_type_String.append("&cate3=" + cate3);
        }

        this.linkParams = "?page_size="+ this.page_size+search_type_String.toString();
        log.info("PageResponseDTO END");
        log.info("========================================");
    }

    public int getTotal_page() {
        return (this.total_count > 0 ?
                (int)Math.ceil(this.total_count/(double)this.page_size) : 1);
    }

    public int getPage_skip_count() {
        return (this.page-1)*this.page_size;
    }

    public void setPage_block_start() {
        this.page_block_start = (
                (int)Math.floor(this.page/(double)this.page_block_size)
                        *this.page_block_size
        ) + 1;
    }

    public void setPage_block_end() {
        this.page_block_end = (
                (int)Math.floor(this.page/(double)this.page_block_size)
                        *this.page_block_size
        ) + this.page_block_size;
        this.page_block_end = ( this.total_page > this.page_block_end ? this.page_block_end : this.total_page);
    }
}
