package data.info_service.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : data.info_service.controller
 * fileName       : FileUploadController
 * author         : sanghyeok
 * date           : 2024/05/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/09        sanghyeok       최초 생성
 */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("/fileUploadV1")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        log.info("file upload = {}", file.getOriginalFilename());
        log.info("file suffix = {}", suffix);

        if(!suffix.equalsIgnoreCase("xlsm") || !suffix.equalsIgnoreCase("xls")) {
            //처리
        }

        // 처리

        List result = new ArrayList();

        model.addAttribute("result", null);

        return "tax/taxV1Result";
    }
}
