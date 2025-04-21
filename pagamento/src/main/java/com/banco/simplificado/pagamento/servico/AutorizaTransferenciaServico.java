package com.banco.simplificado.pagamento.servico;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class AutorizaTransferenciaServico {

	private final WebClient webClient;
	
	public AutorizaTransferenciaServico(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://util.devi.tools/api/v2/authorize").build();
    }
	
	public Mono<Boolean> verificarAutorizacao() {
        return webClient.get()
               .uri("/authorize")
               .retrieve()
               .bodyToMono(AutorizacaoTransferenciaResponse.class)
               .map(resp -> resp.data().authorization())
               .onErrorReturn(false); // se der erro, considera não autorizado
    }
	
	/*Utilizando Record java+17 para não criar mais classe de dominio.
	 * 
	 * TODO Utilizar também para recebimento de pagamento.*/
	private record AutorizacaoTransferenciaResponse(
		    String status,
		    Data data
		) {
		    public record Data(boolean authorization) {}
		}
}
