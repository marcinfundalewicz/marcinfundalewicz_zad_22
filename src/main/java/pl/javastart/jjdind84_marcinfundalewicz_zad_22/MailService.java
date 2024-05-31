package pl.javastart.jjdind84_marcinfundalewicz_zad_22;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender mailSender;
    private String companyMail = "user123@interia.eu";

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void sendMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getSenderEmail());
        message.setTo(companyMail);
        message.setSubject(mail.getName());
        message.setText(mail.getContent());
        mailSender.send(message);
    }
}