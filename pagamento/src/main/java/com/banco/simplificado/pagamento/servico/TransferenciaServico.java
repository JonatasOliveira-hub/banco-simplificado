package com.banco.simplificado.pagamento.servico;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.simplificado.pagamento.dominio.Usuario;
import com.banco.simplificado.pagamento.persistencia.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class TransferenciaServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Transactional
	public void efetuarTransferencia(Long idPagador, Long idRecebedor, BigDecimal saldo) {
		Usuario pagador = usuarioRepositorio.findById(idPagador)
				.orElseThrow(() -> new RuntimeException("Usuario pagador não encontrado."));
		Usuario recebedor = usuarioRepositorio.findById(idRecebedor)
				.orElseThrow(() -> new RuntimeException("Usuario recebedor não encontrado."));

		if (!pagador.enviarDinheiro()) {
			throw new RuntimeException("Usuario não pode enviar dinheiro.");
		}

		if (pagador.getSaldo().compareTo(saldo) < 0) {
			throw new RuntimeException("Saldo insuficiente.");
		}

		pagador.setSaldo(pagador.getSaldo().subtract(saldo));
		recebedor.setSaldo(recebedor.getSaldo().add(saldo));

		usuarioRepositorio.save(pagador);
		usuarioRepositorio.save(recebedor);
	}
}
