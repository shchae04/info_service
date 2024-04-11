package data.info_service.controller;

import data.info_service.entity.Member;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {


    @Autowired
    JavaMailSender mailSender;

    static {
        log.info("AuthController Created");
    }

    @GetMapping("/verify")
    public Object verify(@RequestParam("token") String token, Member member) {
        if (token == null) return "Invalid Token";


//        if (token.equalsIgnoreCase(member.getToken())) {
        if (token.equalsIgnoreCase("2222")) {
            // 이용 가능
            log.info("Verify Success");
            return "ACCEPT";

        }

        return InvalidPropertyException.class;

    }

    @PostMapping("/sendMail")
//    public Object signup(String nickName, String email, String password, HttpSession session) throws Exception{
    public Object signup() throws Exception {

        String token = UUID.randomUUID().toString();

        log.info("token={}", token);



        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, "shchae04@naver.com");
        message.setSubject("SH CORP MAIL AUTHENTICATION");
        String body = "<div>"
                + "<h1> 안녕하세요. SH Corporation 입니다</h1>"
                + "<br>"
                + "<p>아래 링크를 클릭하면 이메일 인증이 완료됩니다.<p>"
                + "<button><a href='http://localhost:8080/auth/verify?token=2222'>"
                + "인증</a></button>"
                + "</div>";
        message.setText(body, "utf-8", "html");
        message.setFrom(new InternetAddress("shchae04@naver.com", "SYS_SH_CORP"));
        mailSender.send(message);
        return "OK";
    }

}
