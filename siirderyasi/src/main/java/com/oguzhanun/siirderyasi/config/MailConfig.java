package com.oguzhanun.siirderyasi.config;


import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailConfig {
	
	@Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        System.out.println("mailconfig içindeyiz...");
        //javaMailSender.setProtocol("smtp");
        //javaMailSender.setHost("127.0.0.1");
        
        //javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("mustafaun.1001@gmail.com");
        javaMailSender.setPassword("16mart2008");
        javaMailSender.setDefaultEncoding("utf-8");
        
    
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");//smtp.live.com
        properties.put("spring.mail.protocol", "smtp");
        //properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 465);//465 veya 25 hotmail için
        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.timeout", 10000);
        
        
        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.ssl.enable", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.debug", "true");
        
        javaMailSender.setJavaMailProperties(properties);
        
        System.out.println("mailconfig den çıkıyoruz");
        return javaMailSender;
    }

}
