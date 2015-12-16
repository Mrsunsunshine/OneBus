package com.onebus.others;

import java.util.Date; 
import java.util.Properties; 
import javax.mail.Address; 
import javax.mail.Authenticator; 
import javax.mail.Message; 
import javax.mail.PasswordAuthentication; 
import javax.mail.Session; 
import javax.mail.Transport; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage; 
 
 
public class SendEmail 
{ 
    //mail server 
    private String host = "smtp.sina.cn"; 
    //mail account  
    private String username = "onebus@sina.cn"; 
    //password 
    private String password = "one123456";    
    private String mail_head_name = "Head of test mail"; 
    private String mail_head_value = "Head of test mail"; 
    private String mail_to = " "; 
    private String mail_from = "onebus@sina.cn"; 
    private String mail_subject = "subject of test mail"; 
    private String mail_body = " "; 
    private String personalName = "onebus"; 
 
    
    /**
     * @param email //收件人邮箱
     * @param content //信息内容
     * @param mailSubject //主题
     * @throws Exception
     */
    public void send(String email,String content, String mailSubject) throws Exception 
    { 
        try 
        { 
        	mail_to = email;
        	mail_body = content;
        	mail_subject = mailSubject;
            Properties props = new Properties(); // 获取系统环境 
            Authenticator auth = new Email_Autherticator(); // 认证 
            props.put("mail.smtp.host", host); 
            props.put("mail.smtp.auth", "true"); 
            Session session = Session.getDefaultInstance(props, auth); 
            //设置session,和邮件服务器进行通讯。 
            MimeMessage message = new MimeMessage(session); 
           
            message.setSubject(mail_subject);  
            message.setText(mail_body); // 设置邮件正文 
            message.setHeader(mail_head_name, mail_head_value); // 设置邮件标题 
            message.setSentDate(new Date()); // 设置邮件发送日期 
            Address address = new InternetAddress(mail_from, personalName); 
            message.setFrom(address); // 设置邮件发送者的地址 
            Address toAddress = new InternetAddress(mail_to); // 设置邮件接收方的地址 
            message.addRecipient(Message.RecipientType.TO, toAddress); 
            Transport.send(message); // 发送邮件 
            System.out.println("send over!"); 
        } catch (Exception ex) 
        { 
            ex.printStackTrace(); 
            throw new Exception(ex.getMessage()); 
        } 
    } 
 
    /*
     * 用来进行服务器对用户的认证
     */ 
    public class Email_Autherticator extends Authenticator 
    { 
        public Email_Autherticator() 
        { 
            super(); 
        } 
 
        public Email_Autherticator(String user, String pwd) 
        { 
            super(); 
            username = user; 
            password = pwd; 
        } 
 
        public PasswordAuthentication getPasswordAuthentication() 
        { 
            return new PasswordAuthentication(username, password); 
        } 
    } 

 
}
