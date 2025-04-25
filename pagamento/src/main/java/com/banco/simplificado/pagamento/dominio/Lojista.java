package com.banco.simplificado.pagamento.dominio;

import jakarta.persistence.Entity;

@Entity
// Não deve ser utilizada na estratégia descrita na calsse Usuario @DiscriminatorValue("LOJISTA")
public class Lojista extends Usuario{

	@Override
	public boolean enviarDinheiro() {
		return false;
	}

	
}
