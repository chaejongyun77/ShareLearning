package com.fullstack4.sharelearning.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @RequestMapping(value = "/")
    public String indexView(Model model){
        System.out.println("인덱스 컨트롤러임");

        return "index";
    }
}
