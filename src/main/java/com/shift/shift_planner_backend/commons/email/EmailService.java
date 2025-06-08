package com.shift.shift_planner_backend.commons.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSenderImpl mailSender;
    private final ResourceLoader resourceLoader;

    public EmailService(JavaMailSenderImpl mailSender, ResourceLoader resourceLoader) {
        this.mailSender = mailSender;
        this.resourceLoader = resourceLoader;

    }


    public void sendTemplateEmail(EmailTemplate emailTemplate, String to, String subject, Map<String, String> replaces) {
        String template = getTemplate(emailTemplate, replaces);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(template, true);


            mailSender.send(mimeMessage);
            log.info("{} email sent to {}", emailTemplate.name(), to);

        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email: ", e);
        }
    }

    public String getTemplate(EmailTemplate emailTemplate, Map<String, String> replaces) {
        String templatePath = "classpath:/templates/" + emailTemplate.getTemplateName();
        Resource resource = resourceLoader.getResource(templatePath);

        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        ) {

            String content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            for (Map.Entry<String, String> entry : replaces.entrySet()) {
                String placeholder = "{{" + entry.getKey() + "}}";
                content = content.replace(placeholder, entry.getValue());
            }

            return content;

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo HTML: " + templatePath, e);
        }
    }
}