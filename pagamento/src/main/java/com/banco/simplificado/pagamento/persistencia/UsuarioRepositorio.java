package com.banco.simplificado.pagamento.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.simplificado.pagamento.dominio.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	public interface UserRepository extends JpaRepository<Usuario, Long> {
	    Optional<Usuario> findByEmail(String email);
	    Optional<Usuario> findByCpfCnpj(String cpfCnpj);
	}
}
