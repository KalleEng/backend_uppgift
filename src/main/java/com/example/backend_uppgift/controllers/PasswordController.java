package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.Services.Impl.EmailService;
import com.example.backend_uppgift.Services.Impl.PasswordResetTokenService;
import com.example.backend_uppgift.models.PasswordResetToken;
import com.example.backend_uppgift.security.User;
import com.example.backend_uppgift.security.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reset-password")
public class PasswordController {
    private final PasswordResetTokenService tokenService;
    List<String> errorList = new ArrayList<>();
    private final UserRepo userRepo;
    public PasswordController(PasswordResetTokenService tokenService, UserRepo userRepo) {
        this.tokenService = tokenService;
        this.userRepo = userRepo;
    }

    @RequestMapping("/show-form")
    public String showPasswordForm(){
        return "resetPasswordForm";
    }

    @PostMapping("/send-token")
    public String sendResetToken(@RequestParam("email") String email, Model model) {

        User user = userRepo.getUserByUsername(email);

        if (user != null) {
            tokenService.createToken(user);
            return "redirect:/login";
        } else {
            errorList.add("User doesn't exist.");
            model.addAttribute("errors", errorList);
            return resetPasswordError(model);
        }
    }

    @RequestMapping("/{token}")
    public String forgotPassword(@PathVariable("token") String token, Model model){
        PasswordResetToken passwordResetToken = tokenService.getToken(token);
        if (passwordResetToken == null){
            errorList.add("Token is not valid.");
            model.addAttribute("errors", errorList);
            return resetPasswordError(model);
        }
        model.addAttribute("token", passwordResetToken.getToken());
        return "enterNewPassword";
    }


    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password){

        PasswordResetToken passwordResetToken = tokenService.getToken(token);
        if (passwordResetToken == null || tokenService.isTokenExpired(passwordResetToken)) {
            return "redirect:/reset-error";
        }

        User user = passwordResetToken.getUser();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);
        user.setPassword(hash);
        userRepo.save(user);
        tokenService.deleteToken(passwordResetToken);
        return "redirect:/login";
    }

    @RequestMapping("/reset-error")
    public String resetPasswordError(Model model){
        return "resetError";
    }
}
