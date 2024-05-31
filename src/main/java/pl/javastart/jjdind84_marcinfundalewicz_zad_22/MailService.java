package pl.javastart.jjdind84_marcinfundalewicz_zad_22;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String companyMail;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void sendMail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(companyMail);
        //wyslanie do wlasciciela strony
        message.setTo("fakekonto@gmail.com");
        message.setReplyTo(mailDto.getSenderEmail());
        message.setSubject(mailDto.getName());
        message.setText(mailDto.getContent());
        mailSender.send(message);

        if(mailDto.isConfirmation()) {
            SimpleMailMessage message2 = new SimpleMailMessage();
            message2.setFrom(companyMail);
            //wyslanie do osoby z formularza
            message2.setTo(mailDto.getSenderEmail());
            message2.setReplyTo(companyMail);
            message2.setSubject(mailDto.getName());
            message2.setText("Potwierdzenie otrzymania maila");
            mailSender.send(message2);
        }
    }
}