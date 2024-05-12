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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

            HttpSession session = req.getSession();
            session.setAttribute("memberDTO",LoginMemberDTO);
            
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

            Map<String, Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("redirect", "/");

            return ResponseEntity.ok().body(resp);




        }

        System.out.println("로그인실패");
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", false);
        resp.put("message", "로그인 실패.");
        return ResponseEntity.ok().body(resp);
    }
}
