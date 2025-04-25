package com.banco.simplificado.pagamento.controlador;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.simplificado.pagamento.servico.TransferenciaServico;

@RestController
@RequestMapping(value = "/transferencias")
public class TransferenciaControlador {

	@Autowired
	private TransferenciaServico transfereciaServico;

	@PostMapping
	public ResponseEntity<String> transferir(@RequestBody TransferenciaRequest transferenciaRequest) {
		final Long pagadorId = transferenciaRequest.pagadorId();
		final Long recebedorId = transferenciaRequest.recebedorId();
		final BigDecimal valor = transferenciaRequest.valor();

		try {
			transfereciaServico.realizarTransferencia(pagadorId, recebedorId, valor);
			return ResponseEntity.status(HttpStatus.CREATED).body("Transferência realizada com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

	}

	private record TransferenciaRequest(BigDecimal valor, Long pagadorId, Long recebedorId) {}
	
}
