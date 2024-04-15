package data.info_service.controller;

import data.info_service.service.MemberService;
import data.info_service.service.MemberService.MemberRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String joinMember(@ModelAttribute MemberRegisterRequestDto member) {
        log.info("join 요청");
        log.info("member={}", member);

        memberService.registerMember(member);
        return "home";
    }
}
