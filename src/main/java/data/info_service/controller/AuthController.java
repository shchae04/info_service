package data.info_service.controller;

import data.info_service.Member;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/sendMail")
//    public Object signup(String nickName, String email, String password, HttpSession session) throws Exception{
    public Object signup() throws Exception{

        String token = UUID.randomUUID().toString();

        Member member = new Member();
//        member.setNickName(nickName);
//        member.setEmail(email);
//        member.setPassword(password);
        member.setToken(token);

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, "shchae04@naver.com");
        message.setSubject("공고문 올라옴");
        String body = "<div>"
                + "<h1> 안녕하세요. INFO_SERVICE 입니다</h1>"
                + "<br>"
                + "<p>아래 링크를 클릭하면 이메일 인증이 완료됩니다.<p>"
                + "</div>";
        message.setText(body, "utf-8", "html");
        message.setFrom(new InternetAddress("shchae04@naver.com", "SYS_ADMIN"));
        mailSender.send(message);
        return "OK";
    }

}
