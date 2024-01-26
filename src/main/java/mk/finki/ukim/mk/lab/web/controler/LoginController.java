package mk.finki.ukim.mk.lab.web.controler;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = null;
        try {
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));

            Cookie userIDCookie = new Cookie("user-id", user.getId().toString());
            userIDCookie.setMaxAge(3600);
            response.addCookie(userIDCookie);
//            request.getSession().setAttribute("user", user);
            return "redirect:/balloons";
        } catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
