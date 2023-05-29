package no.ntnu.ProFlex.controllers.web;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.UserService;
import no.ntnu.ProFlex.services.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserService userService;

    @GetMapping("/forgotpassword")
    public String showForgotPasswordForm() {
        return "forgot-password-form";
    }

    @PostMapping("/forgotpassword")
    public String proccessForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            this.userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/resetpassword?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
        } catch (NullPointerException | MessagingException | UnsupportedEncodingException exception) {
            model.addAttribute("error", exception.getMessage());
        }
        return "forgot-password-form";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@shopme.com", "Shopme Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @GetMapping("/resetpassword")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = this.userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
        return "reset-password-form";
    }

    @PostMapping("/resetpassword")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = this.userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            this.userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "login";
    }

}
