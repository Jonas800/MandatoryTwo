package mandatory.two.controller;

import mandatory.two.model.User;
import mandatory.two.passwordHandlers.PasswordMatcher;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    String error = "";

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("error", error);

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "") String email,
                        @RequestParam(defaultValue = "") String password,
                        HttpServletRequest request){

        User user = userRepository.findByEmail(email);

        if(user != null && user.getEmail() != null){
            try {
                if(PasswordMatcher.validatepassword(password, user.getPassword())){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    return "redirect:/forside";
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        error = "Email or password is invalid";

        return "redirect:/login";
    }

}
