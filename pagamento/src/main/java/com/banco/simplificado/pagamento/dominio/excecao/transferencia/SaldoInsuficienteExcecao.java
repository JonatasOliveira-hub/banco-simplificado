package com.banco.simplificado.pagamento.dominio.excecao.transferencia;

public class SaldoInsuficienteExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteExcecao(String mensagem) {
		super(mensagem);
	}

}
