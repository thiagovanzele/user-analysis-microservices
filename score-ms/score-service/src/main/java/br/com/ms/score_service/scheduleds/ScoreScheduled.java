package br.com.ms.score_service.scheduleds;

import br.com.ms.score_service.clients.EmailClient;
import br.com.ms.score_service.models.dto.request.EmailRequest;
import br.com.ms.score_service.models.dto.request.UsuarioRequest;
import br.com.ms.score_service.models.dto.response.UsuarioScore;
import br.com.ms.score_service.services.ScoreService;
import jakarta.annotation.Nonnull;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class ScoreScheduled {

    private final ScoreService scoreService;
    private final EmailClient emailClient;

    public ScoreScheduled(ScoreService scoreService, EmailClient emailClient) {
        this.scoreService = scoreService;
        this.emailClient = emailClient;
    }

    @Scheduled(cron = "0 * * * * *")
    public void enviarEmailsPromocao() {
        List<UsuarioScore> usuarioScores = scoreService.buscarUsuariosPorRendaEUf();
        for (UsuarioScore user : usuarioScores) {

            EmailRequest emailRequest = getEmailRequest(user);

            if (user.score() >= 900) {
                log.info("Enviando email para usuario: {}", user.email());

                emailClient.enviarEmail(emailRequest);

            }

        }
    }

    @Nonnull
    private static EmailRequest getEmailRequest(UsuarioScore user) {
        UsuarioRequest usuarioRequest = new UsuarioRequest(user.nome(), user.email());
        return new EmailRequest(
                usuarioRequest,
                "Promoção de cartão",
                "Parabéns! Identificamos que você possui um excelente score e, por isso, liberamos uma condição especial exclusiva para você. " +
                        "Aproveite essa oportunidade com benefícios diferenciados e acesso a ofertas únicas pensadas especialmente para o seu perfil.");
    }
}
