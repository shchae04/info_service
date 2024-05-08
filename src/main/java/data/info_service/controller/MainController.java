package data.info_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : data.info_service.controller
 * fileName       : MainController
 * author         : sanghyeok
 * date           : 2024/05/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/07        sanghyeok       최초 생성
 * 2024/05/08        sanghyeok       문서 비교 추가
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/main/tax_v1")
    public String taxV1() {
        return "tax/taxV1";
    }
}
