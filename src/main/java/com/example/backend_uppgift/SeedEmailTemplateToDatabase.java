package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.Impl.EmailService;
import com.example.backend_uppgift.models.ContractCustomer;
import com.example.backend_uppgift.models.EmailTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
@ComponentScan
public class SeedEmailTemplateToDatabase implements CommandLineRunner {

    EmailService emailService;

    public SeedEmailTemplateToDatabase(EmailService emailService){
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) throws Exception {
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "    <h1>Tack för din order!</h1>\n" +
                "    <p th:text=\"'Hej ' + ${customerName} + '!'\" />\n" +
                "    <p th:text=\"'Du har bokat ' + ${numberOfBookedDays} + ' nätter på vårt fina hotell.'\" />\n" +
                "    <p th:text=\"'Från: ' + ${startDate}\" />\n" +
                "    <p th:text=\"'Till: ' + ${endDate}\" />\n" +
                "    <p th:text=\"'Du har bokat för: ' + ${numberOfPeople} + ' personer'\"/>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        EmailTemplate emailTemplate = new EmailTemplate(htmlContent);
        emailService.save(emailTemplate);

    }
}
