package com.shop.service;

import com.shop.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toEmail, String subMessage, String textMessage) {
        // SimpleMailMessage (단순 텍스트 구성 메일 메시지 생성할 때 이용)
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        // 수신자 설정
        simpleMessage.setTo(toEmail);
        // 메일 제목
        simpleMessage.setSubject(subMessage);
        // 메일 내용
        simpleMessage.setText(textMessage);
        // 메일 발송
        javaMailSender.send(simpleMessage);
    }

    public User userAndEmailByPwdReset(User findUser, String toEmail){
        User user = new User();
        user.setUser_id(findUser.getUser_id());
        String subMessage = findUser.getUser_name() + "님의 비밀번호가 변경되었습니다.";
        String pwd = setMailPwd();
        sendMail(toEmail, subMessage, "password:"+pwd);
        user.setUser_pwd(pwd);// 암호화할때 암호화 해야함..
        return user;
    }

    public void sendMailToMultipleRecipients(List<String> toEmails, String subject, String textMessage) {
        for (String toEmail : toEmails) {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            // set recipient email address
            simpleMessage.setTo(toEmail);
            // set subject of the email
            simpleMessage.setSubject(subject);
            // set text content of the email
            simpleMessage.setText(textMessage);
            // send the email
            javaMailSender.send(simpleMessage);
        }
    }

    public String setMailPwd(){
        String pwd = "";
        for (int i = 0; i < 10; i++){
            int randomNum = (int)(Math.random() * 26)+65+i;
            char randomChar = (char)randomNum;
            pwd += randomChar;
        }
        return pwd;
    }
}