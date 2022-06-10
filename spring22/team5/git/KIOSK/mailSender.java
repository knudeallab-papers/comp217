package TeamProject;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class mailSender
{
    // 1. 발신자의 메일 계정과 비밀번호 설정
    private static final String user = "kimjhyun0627@gmail.com";
    private static final String password = "nhvdaeibpmpgdotx";

    private static String mailToSend;

    public mailSender(String mailInput)
    {
        mailToSend = mailInput+"@knu.ac.kr";
    }

    public static String getCode()
    {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        String code = "" + r.nextInt(9999);

        String recipient = mailToSend;

        // 2. Property에 SMTP 서버 정보 설정
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user, password);
            }
        });

        // 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
        // 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.

        MimeMessage message = new MimeMessage(session);
        try
        {
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("[학생회 키오스크] 본인인증 코드입니다");
            message.setText("본인인증 코드는 [" + code + "] 입니다. 코드를 입력란에 입력해주세요.");
            Transport.send(message);
        }
        catch (AddressException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        return code;
    }
}