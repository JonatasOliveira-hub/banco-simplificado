package com.banco.simplificado.pagamento.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banco.simplificado.pagamento.dominio.Usuario;
import com.banco.simplificado.pagamento.dominio.excecao.usuario.CpfCnpjExistenteExcecao;
import com.banco.simplificado.pagamento.dominio.excecao.usuario.EmailExistenteExcecao;
import com.banco.simplificado.pagamento.persistencia.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public Usuario cadastrarUsuario(Usuario usuario) {

		usuarioRepositorio.findByEmail(usuario.getEmail()).ifPresent(u -> {
			throw new EmailExistenteExcecao("E-mail já cadastrado");
		});

		usuarioRepositorio.findByCpfCnpj(usuario.getCpfCnpj()).ifPresent(u -> {
			throw new CpfCnpjExistenteExcecao("CPF/CNJP já cadastrado");
		});

		return usuarioRepositorio.save(usuario);
	}

	public List<Usuario> listarUsuario() {
		return usuarioRepositorio.findAll();
	}

	public Page<Usuario> listarUsuarioPaginado(Pageable pageable) {
		return usuarioRepositorio.findAll(pageable);
	}
}
