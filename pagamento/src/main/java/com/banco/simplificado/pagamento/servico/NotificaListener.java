package com.banco.simplificado.pagamento.servico;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.reactive.function.client.WebClient;

import com.banco.simplificado.pagamento.servico.TransferenciaServico.NotificacaoTransferenciaEvento;

/*Utilizando Listener que consulta API externa para envio de notificação.*/

@Component
public class NotificaListener {

	private final WebClient webClient = WebClient.create("https://util.devi.tools/api/v1");

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void enviarNotificacao(NotificacaoTransferenciaEvento evento) {
		Map<String, String> body = Map.
				of("destinatario", evento.usuarioId(), "mensagem", evento.mensagem());

		webClient.post()
		.uri("/notify")
		.bodyValue(body)
		.retrieve()
		.bodyToMono(String.class)
		.doOnSuccess(resposta -> System.out.println("Notificação enviada com sucesso para o e-mail: " + evento.usuarioId()))
		.doOnError(e -> System.err.println("Erro ao enviar notificação: " + e.getMessage())).subscribe(); 

	}
}
