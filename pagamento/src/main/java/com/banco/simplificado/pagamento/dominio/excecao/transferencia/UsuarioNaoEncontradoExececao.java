package com.banco.simplificado.pagamento.dominio.excecao.transferencia;

public class UsuarioNaoEncontradoExececao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoExececao(String mensagem) {
		super(mensagem);
	}

}
