package com.banco.simplificado.pagamento.dominio.excecao.usuario;

public class CpfCnpjExistenteExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfCnpjExistenteExcecao(String mensagem) {
		super(mensagem);
	}
}
