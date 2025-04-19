package com.banco.simplificado.pagamento.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.simplificado.pagamento.dominio.Usuario;
import com.banco.simplificado.pagamento.servico.UsuarioServico;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioControlador {

	@Autowired
	private UsuarioServico usuarioServico;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		final List<Usuario> usuarios = usuarioServico.listarUsuario();
		return ResponseEntity.ok(usuarios);
	}

	// POST /usuarios — cadastra um novo usuário
	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		final Usuario novoUsuario = usuarioServico.cadastrarUsuario(usuario);
		return ResponseEntity.ok(novoUsuario);
	}
}
