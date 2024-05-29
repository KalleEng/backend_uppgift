package com.example.backend_uppgift.Utils;

import com.example.backend_uppgift.Services.Impl.EmailService;
import com.example.backend_uppgift.models.EmailTemplate;
import com.example.backend_uppgift.repositories.EmailRepo;
import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminController {

    private final EmailService emailService;
    private final EmailRepo emailRepo;
    private final Blacklist blacklist;

    public AdminController(EmailService emailService, EmailRepo emailRepo, Blacklist blacklist) {
        this.emailService = emailService;
        this.emailRepo = emailRepo;
        this.blacklist = blacklist;
    }

    @GetMapping("/save-email-template")
    public String saveEditedEmailTemplate(@RequestParam("text") String text) {
        emailRepo.updateEmailBodyById(1L, text);
        return "redirect:/admin/all";
    }

    @GetMapping("/all")
    public String getEmailBodyFromDatabase(Model model){
        EmailTemplate emailTemplate = emailRepo.findById(1L).orElse(null);
        String emailBody = emailTemplate.getHtmlTemplate();
        model.addAttribute("htmlTemplateFromDB",emailBody);
        return "admin";

    }

    @GetMapping("/update-blacklisted-user")
    public String updateBlacklistedUser(@RequestParam("email") String email,@RequestParam("name") String name,@RequestParam("isBlacklisted") boolean ok) throws IOException, InterruptedException {
        blacklist.upsert(email,name,ok);
        return "redirect:/admin/all";
    }

    @Getter
    static class TextRequest {
        private String text;

        public void setText(String text) {
            this.text = text;
        }
    }
}
