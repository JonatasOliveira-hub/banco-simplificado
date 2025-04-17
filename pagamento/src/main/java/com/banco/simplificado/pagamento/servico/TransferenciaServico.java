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
	private UsuarioRepositorio userRepository;

	@Transactional
	public void efetuarTransferencia(Long idPagador, Long idRecebedor, BigDecimal saldo) {
		Usuario sender = userRepository.findById(idPagador)
				.orElseThrow(() -> new RuntimeException("Usuario pagador não encontrado."));
		Usuario receiver = userRepository.findById(idRecebedor)
				.orElseThrow(() -> new RuntimeException("Usuario recebedor não encontrado."));

		if (!sender.enviarDinheiro()) {
			throw new RuntimeException("User not allowed to send money");
		}

		if (sender.getSaldo().compareTo(saldo) < 0) {
			throw new RuntimeException("Insufficient funds");
		}

		sender.setSaldo(sender.getSaldo().subtract(saldo));
		receiver.setSaldo(receiver.getSaldo().add(saldo));

		userRepository.save(sender);
		userRepository.save(receiver);
	}
}
