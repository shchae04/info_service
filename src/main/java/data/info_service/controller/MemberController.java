package data.info_service.controller;

import data.info_service.dto.MemberLoginRequestDto;
import data.info_service.dto.MemberRegisterRequestDto;
import data.info_service.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String join() {
        // 페이지 생성
        return "member/signup";
    }


    @PostMapping("/login")
    public String login(MemberLoginRequestDto loginRequestDto) {
        memberService.loginMember(loginRequestDto);

        return "redirect:/main";
    }

    @PostMapping("/signup")
    public String joinMember(MemberRegisterRequestDto registerRequestDto) {
        log.info("join 요청");
        memberService.registerMember(registerRequestDto);

        return "main";
    }

}
