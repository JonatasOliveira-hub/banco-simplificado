package com.banco.simplificado.pagamento.servico;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.banco.simplificado.pagamento.dominio.Usuario;
import com.banco.simplificado.pagamento.dominio.excecao.transferencia.SaldoInsuficienteExcecao;
import com.banco.simplificado.pagamento.dominio.excecao.transferencia.UsuarioLojistaExececao;
import com.banco.simplificado.pagamento.dominio.excecao.transferencia.UsuarioNaoEncontradoExececao;
import com.banco.simplificado.pagamento.persistencia.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class TransferenciaServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private AutorizaTransferenciaServico autorizador;

	@Autowired
	private ApplicationEventPublisher publicaEvento;

	@Transactional
	public void realizarTransferencia(Long idPagador, Long idRecebedor, BigDecimal valor) {
		Usuario pagador = usuarioRepositorio.findById(idPagador).orElseThrow(
				() -> new UsuarioNaoEncontradoExececao("Usuario pagador de ID: " + idPagador + " não encontrado."));
		Usuario recebedor = usuarioRepositorio.findById(idRecebedor).orElseThrow(
				() -> new UsuarioNaoEncontradoExececao("Usuario recebedor de ID: " + idRecebedor + " não encontrado."));

		if (!pagador.enviarDinheiro()) {
			throw new UsuarioLojistaExececao("Usuario do tipo Lojista: " + idPagador + " não pode enviar dinheiro.");
		}

		if (pagador.getSaldo().compareTo(valor) < 0) {
			throw new SaldoInsuficienteExcecao("Usuario Pagador com ID: " + idPagador + " não tem saldo suficiente.");
		}

		pagador.setSaldo(pagador.getSaldo().subtract(valor));
		recebedor.setSaldo(recebedor.getSaldo().add(valor));

		if (!autorizador.verificarAutorizacao().block()) {
			throw new RuntimeException("API externa não autorizou a Transferência.");
		} else {
			System.out.println("Tranferência autorizada!!");
			usuarioRepositorio.save(pagador);
			usuarioRepositorio.save(recebedor);
		}
		publicaEvento.publishEvent(new NotificacaoTransferenciaEvento(
			    recebedor.getEmail(),"Você recebeu uma transferência de R$ " + valor));

	}

	/* Utilizando Record java+17 para não criar mais classe de dominio. */
	protected record ConsultaApiExterna(String status, Data data) {
		public record Data(boolean authorization) {
		}
	}

	protected record NotificacaoTransferenciaEvento(String usuarioId, String mensagem) {}

}
