package com.fullstack4.sharelearning.controller;

import com.fullstack4.sharelearning.dto.LoginDTO;
import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.service.LoginServiceIf;
import com.fullstack4.sharelearning.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginServiceIf loginServiceIf;

    @GetMapping("/login")
    public void LoginGET(Model model,
                         HttpServletRequest req){
        String acc_url = req.getHeader("referer");

        String uri = acc_url;


        model.addAttribute("acc_url", uri);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> LoginPost(
            @RequestBody LoginDTO loginDTO,
            HttpServletRequest req,
            HttpServletResponse response,
            @RequestParam(name="acc_url", defaultValue = "/", required = false) String acc_url,
            Model model){



        String save_id = "";
        String uri = acc_url;

        MemberDTO LoginMemberDTO = loginServiceIf.login_info(loginDTO);

        if(LoginMemberDTO !=null) {


            
            //아이디 저장
            if(loginDTO.getSave_id()!=null) {
                save_id=loginDTO.getUser_id();
                CookieUtil.addCookie(response,"save_id",save_id,60*60*24);
                CookieUtil.addCookie(response,"save_id_flag","checked",60*60*24);

            }

            if (loginDTO.getSave_id() == null) {
                CookieUtil.deleteCookie(response, "save_id");
                CookieUtil.deleteCookie(response, "save_id_flag");
            }
            HttpSession session = req.getSession();
            session.setAttribute("memberDTO",LoginMemberDTO);

            //6개월이상 로그인 내역 체크
            LocalDate lastDate = LoginMemberDTO.getLast_date();
            LocalDate currentDate = LocalDate.now();
            // lastDate와 currentDate 사이의 기간을 계산합니다.
            Period period = Period.between(lastDate, currentDate);
            if (period.getMonths() >= 6 || period.getYears() > 0) {
                Map<String, Object> resp = new HashMap<>();
                resp.put("success", false);
                resp.put("message", "6개월 이상 로그인 내역이 없습니다.\n 관리자에게 문의해주십시오.");
                return ResponseEntity.ok().body(resp);

            }

            //로그인 규칙 위반
            if(LoginMemberDTO.getState().equals("N")){
                Map<String, Object> resp = new HashMap<>();
                resp.put("success", false);
                resp.put("message", "관리자 또는 이용 규칙 위반에 의해 이용이 제한된 아이디입니다.\n 관리자에게 문의해 주세요");
                return ResponseEntity.ok().body(resp);


            }

            Map<String, Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("redirect", "/");

            return ResponseEntity.ok().body(resp);




        }

        //로그인 5회 이상 실패 체크
        int fail_count = loginServiceIf.search_fail(loginDTO.getUser_id());
        if(fail_count>=5){
            Map<String, Object> resp = new HashMap<>();
            resp.put("success", false);
            resp.put("message", "5회 이상 로그인 실패로 아이디 잠금 처리된 아이디입니다. 관리자에게 문의해주세요.");
            return ResponseEntity.ok().body(resp);
        }



        System.out.println("로그인실패");
        int fail_result = loginServiceIf.login_fail(loginDTO.getUser_id());
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", false);
        resp.put("message", "로그인 실패.");
        return ResponseEntity.ok().body(resp);
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse response){
        HttpSession session = req.getSession(false);
        if(session!=null) {
            session.invalidate();
        }

        String save_id = CookieUtil.getCookieValue(req,"save_id");
        String save_id_flag = CookieUtil.getCookieValue(req,"save_id_flag");
        if(save_id != null){
            CookieUtil.deleteCookie(response,save_id);
        }
        if(save_id_flag !=null){
            CookieUtil.deleteCookie(response,save_id_flag);
        }



        return "redirect:/";
    }
    @GetMapping("/find")
    public void findPwdGet() {


    }

    @PostMapping("/find")
    @ResponseBody
    public ResponseEntity<?> findPwdPost(@RequestBody LoginDTO loginDTO,HttpServletRequest req,
                            HttpServletResponse response) {
        System.out.println("패스워드찾기 post"+ loginDTO.getUser_id());

        MemberDTO memberDTO = loginServiceIf.find_pwd(loginDTO.getUser_id());
        if(memberDTO != null){
            System.out.println("findController 아이디일치 "+memberDTO.getPwd() );
            Map<String, Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("message"," 임시비밀번호 \n" + memberDTO.getPwd() +" 로 변경되었습니다. \n 다시 로그인해주세요.");
            resp.put("redirect", "/login/login");

            return ResponseEntity.ok().body(resp);
        }
        else{
            System.out.println("findController 아이디불일치 " );
            Map<String, Object> resp = new HashMap<>();
            resp.put("success", false);
            resp.put("message", "일치하는 아이디가 없습니다.");
            return ResponseEntity.ok().body(resp);
        }

    }

    @GetMapping("/update")
    public void updatePwdGet() {


    }

    @PostMapping("/update")
    public void updatePwdPost() {


    }

}
