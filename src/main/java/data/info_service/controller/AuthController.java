package data.info_service.controller;

import data.info_service.entity.Member;
import data.info_service.entity.Role;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

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
    public Object verify(@RequestParam("token") String token) {

        log.info("Verify Token: " + token);

        if (token == null) return "Invalid Token";

        byte[] decodeBytes = Base64.getDecoder().decode(token);
        String decodedToken = new String(decodeBytes);
        String[] tokenParts = decodedToken.split(":");

        if (tokenParts.length != 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Token");
        }

        Member member = Member.builder()
                .email("shchae04@naver.com")
                .role(Role.SYS_USER)
                .password("1111")
                .build();

        String email = tokenParts[0];
        if (!member.getEmail().equalsIgnoreCase(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID EMAIL!!");
        }
        Long tokenTime = Long.parseLong(tokenParts[1]);

        long currentTime = System.currentTimeMillis();
        long diff = currentTime - tokenTime;

        if (diff > 1000L * 60 * 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token Expired");
        }

        log.info("token 인증 성공");
        return ResponseEntity.ok("Verify Success");

    }

    @PostMapping("/sendMail")
    public Object signup() throws Exception {

        String email = "shchae04@naver.com";

        String token = createToken(email);

        log.info("token={}", token);



        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, "shchae04@naver.com");
        message.setSubject("SH CORP MAIL AUTHENTICATION");
        String body = "<div>"
                + "<h1> 안녕하세요. SH Corporation 입니다</h1>"
                + "<br>"
                + "<p>아래 링크를 클릭하면 이메일 인증이 완료됩니다.<p>"
                + "<button><a href='http://localhost:8080/auth/verify?token=" +token +
                "'>"
                + "인증</a></button>"
                + "</div>";
        message.setText(body, "utf-8", "html");
        message.setFrom(new InternetAddress("shchae04@naver.com", "SYS_SH_CORP"));
        mailSender.send(message);
        return "OK";
    }

    public String createToken(String email) {
        long currentTime = System.currentTimeMillis();
        String token = email + ":" + currentTime;

        return Base64.getEncoder().encodeToString(token.getBytes());
    }

}
