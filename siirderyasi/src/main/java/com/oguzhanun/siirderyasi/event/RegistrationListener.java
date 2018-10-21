package com.oguzhanun.siirderyasi.event;


import java.util.UUID;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import com.oguzhanun.siirderyasi.entity.Uye;
import com.oguzhanun.siirderyasi.entity.VerificationToken;
import com.oguzhanun.siirderyasi.service.SiirService;


@Component
@PropertySource({ "classpath:messages_tr.properties" })
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	
	String message;
	
    private VerificationToken verificationToken;
  
    @Autowired
    private SiirService siirService;
    
    @Autowired
    private Environment env;
  
    @Autowired
    private JavaMailSender mailSender;
	
    
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent oRCE) {
		this.confirmRegistration(oRCE);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
        Uye uye = event.getUye();
        String email = uye.getEmail();
        String token = UUID.randomUUID().toString();
        //service.createVerificationToken(user, token);
        verificationToken = new VerificationToken(token,email);
         
        String recipientAddress = email;
        String subject = "Registration Confirmation";
        String confirmationUrl = "http://localhost:9090"+ event.getAppUrl() + "/uyeOnay/" + token;
        message = env.getProperty("message.regSucc") + "\n\n" + confirmationUrl + "\n Bu url'nin gönderilmesini siz sağlamadıysanız, bu maili dikkate almayınız..."; 

        try {
            //CommonsMultipartFile attachFileObj; @RequestParam ile metod argümanı olarak gelen nesne... Jsp sayfasından alınmış örnekte...
             
            mailSender.send(new MimeMessagePreparator() {
            
            	public void prepare(MimeMessage mimeMessage) throws Exception {
 
                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");             
                mimeMsgHelperObj.setTo(recipientAddress);
                mimeMsgHelperObj.setFrom("mustafaun.1001@gmail.com");               
                mimeMsgHelperObj.setText(message);
                mimeMsgHelperObj.setSubject(subject);
 
                // Determine If There Is An File Upload. If Yes, Attach It To The Client Email              
                /*if ((attachFileObj != null) && (attachFileObj.getSize() > 0) && (!attachFileObj.equals(""))) {
                    System.out.println("\nAttachment Name?= " + attachFileObj.getOriginalFilename() + "\n");
                    mimeMsgHelperObj.addAttachment(attachFileObj.getOriginalFilename(), new InputStreamSource() {                   
                        public InputStream getInputStream() throws IOException {
                            return attachFileObj.getInputStream();
                        }
                    });
                } else {
                    System.out.println("\nNo Attachment Is Selected By The User. Sending Text Email!\n");
                }
                */
            	}
            });   
        } catch (Exception messagingException) {
            messagingException.printStackTrace();
        } 
        
        //Service üzerinden verificationToken'ı veri tabanına kaydedeceğiz.
        siirService.setToken(verificationToken);
        
        //DAO üzerinden veri kaydına devam edeceğiz.
        
        //
        
    }
}

/* 
SimpleMailMessage email = new SimpleMailMessage();

email.setFrom("mustafaun.1001@gmail.com");
email.setTo(recipientAddress);
email.setSubject(subject);
email.setText(message + " rn" + "http://localhost:9090" + confirmationUrl);
System.out.println("email gönderme safhasındayiz...");
try {
mailSender.send(email);
} catch(Exception exc) {
	exc.printStackTrace();
}
*/

/*
  //messages.getMessage("message.regSucc", null, event.getLocale());
//        Authenticator authenticator = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication(){
//                return new PasswordAuthentication("mustafaun.1001@gmail.com", "16mart2008");
//            }
//        };
//        Properties properties = new Properties();
//        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
//        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.setProperty("mail.smtp.socketFactory.port", "465");
//        properties.setProperty("mail.smtp.ssl.enable", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "false");
//        properties.setProperty("mail.debug", "true");     
//        properties.put("mail.smtp.host", "smtp.gmail.com");//smtp.live.com
//        properties.put("spring.mail.protocol", "smtp");
//        //properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", 587);//465 veya 25 hotmail için
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.timeout", 10000);
//            
//        Session sessionMail = Session.getInstance(properties,authenticator);
        
        try {
//            Message mimeMessage = new MimeMessage(sessionMail);
//            mimeMessage.setFrom(new InternetAddress("mustafaun.1001@gmail.com"));
//            mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
//            mimeMessage.setSubject("Email Sending via JSP");
//            //mimeMessage.setText("Merhaba. Bu benim ikinci email çalışmam. Kolay Gelsin....");
//            //mimeMessage.setContent("<h1>Merhaba JSP VE HTML</h1>", "text/html");
//              
//            //Bu kısım maile eklenti yapmak için kullanılıyor...
//              // Create the message part 
//              BodyPart messageBodyPart = new MimeBodyPart();
//
//              // Fill the message
//              messageBodyPart.setText("This is message body");
//
//              // Create a multipart message
//              Multipart multipart = new MimeMultipart();
//
//              // Set text message part
//              multipart.addBodyPart(messageBodyPart);
//
//              System.out.println("mesaj gidiyor...");
//              
//            // Send the complete message parts
//            mimeMessage.setContent(multipart );
//            Transport.send(mimeMessage);
            }
        });
        } catch (Exception messagingException) {
            messagingException.printStackTrace();
        } 
 */
