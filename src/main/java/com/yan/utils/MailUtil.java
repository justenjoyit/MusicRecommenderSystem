package com.yan.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by YZT on 2018/5/23.
 */
public class MailUtil {
    public static void sendMail(String email, String code) {
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        try {
            msg.setSubject("邮箱激活");
            final String html =
                    "<html>" +
                            "<head>" +
                            "<meta http-equiv='Content-Type' content='text/html; charset=gb2312'>" +
                            "</head>" +
                            "<body>恭喜你,注册成功,<a href='http://localhost:8080/MusicRecommenderSystem/email/bind/" + email + "/" + code + "'>请点击此链接激活您的帐号!"
                            + "</a></body></html>";
            msg.setContent(html, "text/html;charset=UTF-8");
            msg.setFrom(new InternetAddress("southscut@163.com"));

            Transport transport = session.getTransport();
            transport.connect("southscut@163.com", "PanJian5201314");
            transport.sendMessage(msg, new Address[]{new InternetAddress(email)});

            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
