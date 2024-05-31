package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.models.EmailTemplate;
import com.example.backend_uppgift.repositories.EmailRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


@Service
public class EmailService {

    //@Autowired
    JavaMailSender javaMailSender;
    EmailRepo emailRepo;
    private final TemplateEngine templateEngine;

    public EmailService(TemplateEngine templateEngine, EmailRepo emailRepo) {
        this.templateEngine = templateEngine;
        this.emailRepo = emailRepo;
    }

    public void sendSimpleEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jakob@example.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }


    public void save(EmailTemplate emailTemplate){
        emailRepo.save(emailTemplate);
    }


    public void sendEmail(String to, Map<String, Object> model) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String filePath = "/Users/jakobengwall/Documents/Java/Backend1/Backend_Uppgift/backend_uppgift/src/main/resources/templates/confirmationEmailTemp.html";

        Context context = new Context();
        context.setVariables(model);
        EmailTemplate emailTemplate = emailRepo.findById(1L).orElseThrow(() -> new RuntimeException("Email template not found"));
        String htmlTemplateFromDatabase = emailTemplate.getHtmlTemplate();

        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write(htmlTemplateFromDatabase);
            String htmlContent = templateEngine.process("confirmationEmailTemp", context);
            helper.setTo(to);
            helper.setSubject("Order confirmation");
            helper.setText(htmlContent, true);
            javaMailSender.send(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
