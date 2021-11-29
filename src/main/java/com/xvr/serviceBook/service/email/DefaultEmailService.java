package com.xvr.serviceBook.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailService {

    private final JavaMailSender javaMailSender;

    public DefaultEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(String emailTo, String emailFrom, String text){
        System.out.println("SEND MAIL TO " + emailTo + " text: " + text);
        /*SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailFrom);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setText(text);*/
    }
}
