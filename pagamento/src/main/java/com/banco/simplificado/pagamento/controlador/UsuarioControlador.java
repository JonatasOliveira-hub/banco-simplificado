package com.banco.simplificado.pagamento.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.simplificado.pagamento.dominio.Usuario;
import com.banco.simplificado.pagamento.dominio.excecao.usuario.CpfCnpjExistenteExcecao;
import com.banco.simplificado.pagamento.dominio.excecao.usuario.EmailExistenteExcecao;
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

	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
		try {

			final Usuario novoUsuario = usuarioServico.cadastrarUsuario(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);

		} catch (CpfCnpjExistenteExcecao | EmailExistenteExcecao e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		}
	}

}
