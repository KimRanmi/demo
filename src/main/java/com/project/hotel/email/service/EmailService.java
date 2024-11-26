package com.project.hotel.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // 임시 비밀번호를 포함한 이메일 전송
    public void sendTemporaryPassword(String to, String tempPassword, String userName) throws MessagingException {
        System.out.println("mailsender" +mailSender);
        String subject = "00펜션 임시 비밀번호";
        String text = "<h4>"+userName+"님의 임시 비밀번호는 아래와 같습니다.</h4>"
                + "<h3>" + tempPassword + "</h3>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        System.out.println("되는중..?");
        mailSender.send(message);
        System.out.println(" bb");
    }
}
