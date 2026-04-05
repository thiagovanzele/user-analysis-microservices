package br.com.ms.mail_service.services;

import br.com.ms.mail_service.model.dtos.EmailRequest;
import br.com.ms.mail_service.model.dtos.UsuarioRequest;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender,
                        TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void enviar(EmailRequest request) {

        try {
            Context context = new Context();
            context.setVariable("nome", request.para().nome());
            context.setVariable("mensagem", request.mensagem());

            String html = templateEngine.process("email-promocao", context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(request.para().email());
            helper.setSubject(request.assunto());
            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email", e);
        }
    }
}
