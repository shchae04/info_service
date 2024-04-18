package data.info_service.controller;

import data.info_service.entity.Member;
import data.info_service.entity.Role;
import data.info_service.service.MemberService;
import data.info_service.service.MemberService.MemberRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        // 페이지 생성
        return "member/join";
    }

    @PostMapping("/join")
    @ResponseBody
    public String joinMember(@RequestParam("email") String email, @RequestParam("password") String password) {
        log.info("join 요청");

//        MemberRegisterRequestDto req = new MemberRegisterRequestDto(email, password);

//        memberService.registerMember(req);
        return ResponseEntity.ok("OK").toString();
    }

}
