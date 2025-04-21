package com.banco.simplificado.pagamento.dominio.excecao.usuario;

public class EmailExistenteExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailExistenteExcecao(String mensagem) {
		super(mensagem);
	}
}
