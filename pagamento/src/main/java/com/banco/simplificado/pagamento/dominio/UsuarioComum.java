package com.banco.simplificado.pagamento.dominio;

import jakarta.persistence.Entity;

@Entity
//Não deve ser utilizada na estratégia descrita na calsse Usuario @DiscriminatorValue("COMUM")
public class UsuarioComum extends Usuario {

	@Override
	public boolean enviarDinheiro() {
        return true;
	}

}
