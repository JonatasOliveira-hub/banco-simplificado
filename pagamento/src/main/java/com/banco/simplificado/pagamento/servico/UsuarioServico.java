package com.banco.simplificado.pagamento.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.simplificado.pagamento.dominio.Usuario;
import com.banco.simplificado.pagamento.persistencia.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	//TODO Criar pacote para tratamento de exceções personalizadas.
	public Usuario cadastrarUsuario(Usuario usuario) {

		usuarioRepositorio.findByEmail(usuario.getEmail()).ifPresent(u -> {
			throw new IllegalArgumentException("E-mail já cadastrado");
		});

		usuarioRepositorio.findByCpfCnpj(usuario.getCpfCnpj()).ifPresent(u -> {
			throw new IllegalArgumentException("CPF/CNJP já cadastrado");
		});
		
		return usuarioRepositorio.save(usuario);
	}
	
	public List<Usuario> listarUsuario(){
		//TODO Inserir paginação, caso a qtd de usuarios seja muito grande, sempre exibir 20 por vez.
		return usuarioRepositorio.findAll();
	}
}
