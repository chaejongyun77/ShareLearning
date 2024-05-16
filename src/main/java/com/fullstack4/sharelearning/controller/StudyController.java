package com.fullstack4.sharelearning.controller;

import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.dto.PageRequestDTO;
import com.fullstack4.sharelearning.dto.PageResponseDTO;
import com.fullstack4.sharelearning.dto.StudyDTO;
import com.fullstack4.sharelearning.service.MemberServiceIf;
import com.fullstack4.sharelearning.service.StudyServiceIf;
import com.fullstack4.sharelearning.util.CommonFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final StudyServiceIf studyService;
    private final MemberServiceIf memberService;
    private final CommonFileUtil commonFileUtil;

    @Autowired
    ServletContext servletContext;

    @GetMapping("/today")
    public void todayGET(HttpServletRequest req,
                          Model model
                         ){

        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();
        List<StudyDTO> dto = studyService.study_info(user_id);
        model.addAttribute("studyDTO", dto);

    }

 /*   @GetMapping("/today")
    public void todayGET(HttpServletRequest req,
                         Model model,
                         @RequestParam(name = "date", required = false) String dateStr) {
        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();

        // 날짜가 지정되지 않았을 경우 오늘 날짜를 사용합니다.
        LocalDate date = (dateStr != null) ? LocalDate.parse(dateStr) : LocalDate.now();
        System.out.println("date : " + date);
        System.out.println("user_id : " + user_id);

        // 수정된 부분: studyService.study_info 메서드에 사용자 ID와 선택된 날짜를 전달합니다.
        // studyService 메서드가 날짜에 따라 데이터를 필터링하도록 수정되어야 합니다.
        List<StudyDTO> dto = studyService.study_info(user_id, date);
        model.addAttribute("studyDTO", dto);
      *//*  model.addAttribute("selectedDate", date.toString()); // 선택된 날짜도 모델에 추가합니다.*//*
    }*/

    @PostMapping("/today")
    public void todayPOST(){



    }

    @GetMapping("/mystudy")
    public void mystudyGET(HttpServletRequest req,
                           Model model, PageRequestDTO
                                       pageRequestDTO){
        PageResponseDTO<StudyDTO> responseDTO =studyService.bbsListByPage(pageRequestDTO);



        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/regist")
    public void registGET(HttpServletRequest req,Model model){
        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();

        List<MemberDTO> list = memberService.member_info(user_id);

        model.addAttribute("member",list);
    }

    @PostMapping("/regist")
    public String registPOST(StudyDTO studyDTO,MultipartHttpServletRequest files){

        List<String> filenames = null;
        /*String realPath = servletContext.getRealPath("/resources/upload");*/
        String realPath ="D:\\java4\\sharelearn\\src\\main\\webapp\\resources\\upload";
        System.out.println("realPath" + realPath);

    /*    String saveDirectory = "/resources/upload";*/
        /*String saveDirectory = "D:\\java\\Learning\\ShareLearning\\src\\main\\webapp\\resources\\upload";*/
       filenames = commonFileUtil.fileuploads(files,realPath);

       studyDTO.setImg(filenames.get(0));


        int result = studyService.regist(studyDTO);




     /*   int result = bbsServiceIf.regist(bbsDTO);
        log.info("test result : "+result);
        if(result > 0 ){
            if(filenames!=null) {
                for (String filename : filenames) {
                    BbsFileDTO bbsFileDTO = BbsFileDTO.builder().bbs_idx(bbsFileServiceIf.lastindex()).file_directory(saveDirectory)
                            .file_name(filename).build();
                    int file_result = bbsFileServiceIf.regist(bbsFileDTO);
                }
            }*/



        return "redirect:/study/mystudy";
    }

    @GetMapping("/view")
    public void detailGET(HttpServletRequest req,Model model,@RequestParam("no")int no){
    /*    String realPath = "D:\\java4\\sharelearn\\src\\main\\webapp\\resources\\upload";*/
        StudyDTO studyDTO = studyService.view(no);
/*        studyDTO.setImg(realPath+"\\" + studyDTO.getImg());*/

        System.out.println("studyDTO img : " + studyDTO.getImg());
        System.out.println("view stduyDTO : " + studyDTO);

        model.addAttribute("studyDTO",studyDTO);

    }

    @GetMapping("/share")
    public void shareGET(HttpServletRequest req,
                         Model model, PageRequestDTO
                                     pageRequestDTO){

        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();
        pageRequestDTO.setUser_id(user_id);

        PageResponseDTO<StudyDTO> responseDTO =studyService.shareListByPage(pageRequestDTO,user_id);

        model.addAttribute("responseDTO",responseDTO);



    }
}
