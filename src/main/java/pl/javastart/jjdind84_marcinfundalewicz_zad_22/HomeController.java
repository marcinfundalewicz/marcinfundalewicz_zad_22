package pl.javastart.jjdind84_marcinfundalewicz_zad_22;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private MailService mailService;

    public HomeController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/kontakt")
    public String contact(Model model) {
        model.addAttribute("mail", new Mail());
        return "contact";
    }

    @PostMapping("/send")
    public String send(Mail mail) {

        try {
            mailService.sendMail(mail);

            if (mail.isConfirmation()) {
                return "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "home";
    }
}