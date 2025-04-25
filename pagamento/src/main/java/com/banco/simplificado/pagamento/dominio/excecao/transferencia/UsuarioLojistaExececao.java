package com.banco.simplificado.pagamento.dominio.excecao.transferencia;

public class UsuarioLojistaExececao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioLojistaExececao(String mensagem) {
		super(mensagem);
	}

}
