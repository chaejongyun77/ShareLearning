package com.fullstack4.sharelearning.controller;

import com.fullstack4.sharelearning.dto.*;
import com.fullstack4.sharelearning.service.MemberServiceIf;
import com.fullstack4.sharelearning.service.StudyServiceIf;
import com.fullstack4.sharelearning.util.CommonFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                          Model model,String reg_date
                         ){

        // reg_date가 null이거나 빈 문자열인 경우 오늘 날짜를 기본값으로 설정
        if (reg_date == null || reg_date.isEmpty()) {
            // 오늘 날짜를 yyyy-MM-dd 형식으로 설정
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            reg_date = today.format(formatter);
        }

        System.out.println("reg_Date" +reg_date);
        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();
        List<StudyDTO> dto = studyService.study_info(user_id,reg_date); //수정
        model.addAttribute("studyDTO", dto);
        model.addAttribute("reg_date", reg_date);

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
                                       pageRequestDTO,@RequestParam(name="page_size",defaultValue = "10") String page_size
                           ){
      /*  if(pageRequestDTO.getSearch_type()==null){
            String[] defaultSearchType = new String[] { "f" }; // 기본값 배열 생성
            pageRequestDTO.setSearch_type(defaultSearchType); // 기본값 설정
        }*/
        HttpSession session = req.getSession(false);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        pageRequestDTO.setUser_id(memberDTO.getUser_id());
        if(page_size.equals("개수") || page_size==null || page_size=="" || page_size.equals("10")){
            pageRequestDTO.setPage_size(10);
        }
        System.out.println("pageRequestDTO " + pageRequestDTO);

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
       /* String realPath = servletContext.getRealPath("/resources/upload");*/
        String realPath ="D:\\java4\\sharelearn\\src\\main\\webapp\\resources\\upload"; // 강의실에선 이 경로로 변경
     /*   String realPath =" D:\\java\\Learning\\ShareLearning\\src\\main\\webapp\\resources\\upload";*/

        System.out.println("realPath" + realPath);

    /*    String saveDirectory = "/resources/upload";*/
        /*String saveDirectory = "D:\\java\\Learning\\ShareLearning\\src\\main\\webapp\\resources\\upload";*/
       filenames = commonFileUtil.fileuploads(files,realPath);
        System.out.println("filenames"+filenames);
       studyDTO.setImg(filenames.get(0));

        System.out.println("파일 등록 이 안된거임");
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

        List<StudyDTO> dtoList = responseDTO.getDtoList();
        model.addAttribute("dtoList", dtoList);


        model.addAttribute("responseDTO",responseDTO);
     /*   List<StudyDTO> dto = responseDTO.getDtoList();
        model.addAttribute("dto",dto);*/



    }

    @GetMapping("/shared")
    public void sharedGET(HttpServletRequest req,
                         Model model, PageRequestDTO
                                 pageRequestDTO){

        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();
        pageRequestDTO.setUser_id(user_id);

        PageResponseDTO<StudyDTO> responseDTO =studyService.sharedListByPage(pageRequestDTO,user_id);

        model.addAttribute("responseDTO",responseDTO);



    }
    @GetMapping("/modify")
    public void modifyGET(HttpServletRequest req,Model model,@RequestParam("no")int no){

        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();

        List<MemberDTO> list = memberService.member_info(user_id);


        StudyDTO studyDTO = studyService.view(no);
        model.addAttribute("member",list);
        model.addAttribute("studyDTO",studyDTO);

    }

    @PostMapping("/modify")
    public String modifyPost(StudyDTO studyDTO){

        int result = studyService.modify(studyDTO);

        return "redirect:/study/view?no="+studyDTO.getNo();

    }

    @GetMapping("/delete")
    public String deleteGET(HttpServletRequest req,Model model,@RequestParam("no")int no){

        System.out.println("delete no : " + no);
        HttpSession session = req.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();

        int result = studyService.delete_study(no);
        System.out.println("result delete" + no);


        return "redirect:/study/mystudy";


    }

    @PostMapping("/like")
    @ResponseBody
    public ResponseEntity<?> likePost(@RequestParam("study_idx") int study_idx,HttpServletRequest req,
                            Model model){
        System.out.println("like post로 들어옴?");

        HttpSession session = req.getSession(false);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String user_id = memberDTO.getUser_id();
        System.out.println("like controller: " + user_id +" " + study_idx);

        int result = studyService.insert_like(user_id,study_idx);
        System.out.println("controller like result : " + result );

        if(result==0){


            Map<String, Object> resp = new HashMap<>();
            resp.put("success", false);
            resp.put("message","중복으로 추천을 할 수 없습니다.");

            return ResponseEntity.ok().body(resp);
        }
        else{
            Map<String, Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("message","추천완료");
            resp.put("redirect","/study/view?no="+study_idx);

            return ResponseEntity.ok().body(resp);
        }



    }





}
