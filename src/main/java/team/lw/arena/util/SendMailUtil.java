package team.lw.arena.util;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMailUtil extends Thread {
    private String securityCode;
    private String userEmail;
    public SendMailUtil(String securityCode ,String userEmail){
        this.securityCode=securityCode;
        this.userEmail=userEmail;
    }


    @Override
    public void run() {
        try {
            Session session = Session.getInstance(PropertiesUtil.getPropertiesConnect());
            session.setDebug(true);
            Transport ts = session.getTransport();
            ts.connect(PropertiesUtil.getAccountHost(), PropertiesUtil.getAccountUsername(), PropertiesUtil.getAccountPassword());
            Message message = createEmail(session);
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private Message createEmail(Session session) throws Exception {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(PropertiesUtil.getAccountForm()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject("用户注册邮件");
        String info = "欢迎注册,您的验证码是"+securityCode;
        message.setContent(info, "text/html;charset=UTF-8");
        message.saveChanges();
        return message;
    }
}
