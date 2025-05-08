package com.service.management.system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *  메일에는 총 3개의 프로토콜이 존재한다.
 * */
@Service
@RequiredArgsConstructor
public class MailService {
    private static final String ACCOUNT_EMAIL = "theleanstar@naver.com";
    private final JavaMailSender mailSender;
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(ACCOUNT_EMAIL);
        mailSender.send(message);
    }
}

