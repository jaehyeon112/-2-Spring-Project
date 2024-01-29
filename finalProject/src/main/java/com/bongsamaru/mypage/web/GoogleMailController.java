package com.bongsamaru.mypage.web;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/email")
public class GoogleMailController {

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${spring.mail.password}")
    private String senderPassword;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String mailSmtpStarttlsRequired;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String mailSmtpSocketFactoryClass;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
    private String mailSmtpSocketFactoryFallback;

    // 랜덤한 인증번호4자리 숫자 생성
    private Random random = new Random();
    private int randomNumber = random.nextInt(9000) + 1000;
    
    // 이메일 전송 후 인증 및 이메일 변경을 위한 페이지
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("recipientEmail") String recipientEmail, @RequestParam("verificationCode") String verificationCode) {
        try {
            int code = Integer.parseInt(verificationCode);
            if (code == randomNumber) {
                // 인증 성공
                // 이메일 변경 로직 추가
                return ResponseEntity.ok("success");
            } else {
                // 인증 실패
                return ResponseEntity.ok("failure");
            }
        } catch (Exception e) {
            return ResponseEntity.ok("error");
        }
    }

    // 이메일 전송
    @GetMapping("/send")
    public String sendEmail(Model model, @RequestParam("recipientEmail") String recipientEmail) {
        try {
            // 이메일 설정 및 인증
            Properties props = new Properties();
            props.put("mail.smtp.auth", mailSmtpAuth);
            props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
            props.put("mail.smtp.host", mailHost);
            props.put("mail.smtp.port", mailPort);
            props.put("mail.smtp.socketFactory.class", mailSmtpSocketFactoryClass);
            props.put("mail.smtp.socketFactory.fallback", mailSmtpSocketFactoryFallback);

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            // 이메일 작성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("행복마루 이메일 인증입니다.");
            
            // 이메일 내용
            String emailContent = "인증번호: " + randomNumber;
            message.setText(emailContent);

            // 이메일 전송
            Transport.send(message);

            model.addAttribute("message", "이메일이 성공적으로 전송되었습니다.");
        } catch (Exception e) {
            model.addAttribute("message", "이메일 전송 중 오류가 발생했습니다.");
        }
        return "profile";
    }

}