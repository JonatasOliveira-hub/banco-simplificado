package com.banco.simplificado.pagamento;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.banco.simplificado.pagamento.dominio.Usuario;
import com.banco.simplificado.pagamento.dominio.UsuarioComum;
import com.banco.simplificado.pagamento.dominio.excecao.usuario.CpfCnpjExistenteExcecao;
import com.banco.simplificado.pagamento.dominio.excecao.usuario.EmailExistenteExcecao;
import com.banco.simplificado.pagamento.persistencia.UsuarioRepositorio;
import com.banco.simplificado.pagamento.servico.UsuarioServico;

@ExtendWith(SpringExtension.class)
public class CadastroUsuarioTeste {

	@Mock
	private UsuarioRepositorio usuarioRepositorio;

	@InjectMocks
	private UsuarioServico usuarioServico;

	// Utilizado para dizer para o JUnit que aqui é uma Subclasse.
	@Nested
	class criarUsuario {

		@Test
		@DisplayName("Verificando duplicidade de e-mail.")
		void naoDeveCadastrarUsuarioPorDuplicidadeEmail() {

			// Arrange
			Usuario input = new UsuarioComum();
			input.setCpfCnpj("11111111111");
			input.setEmail("maria@email.com");
			input.setId(1L);
			input.setNomeCompleto("Maria da Silva");
			input.setSaldo(new BigDecimal("1000.00"));
			input.setSenha("123456");
			
			Optional <Usuario> optUser = Optional.of(input);
	        when(usuarioRepositorio.findByEmail("maria@email.com")).thenReturn(optUser);
			doReturn(input).when(usuarioRepositorio).save(any());
			
			Usuario usuarioEmailDuplicado = new UsuarioComum();
			usuarioEmailDuplicado.setCpfCnpj("123445678");
			usuarioEmailDuplicado.setEmail("maria@email.com");
			usuarioEmailDuplicado.setId(1L);
			usuarioEmailDuplicado.setNomeCompleto("Maria da Silva");
			usuarioEmailDuplicado.setSaldo(new BigDecimal("1000.00"));
			usuarioEmailDuplicado.setSenha("123456");
			
			// Act e Assert - Retornando exceção correta em  caso de e-mail existente
			assertThrows(EmailExistenteExcecao.class,() -> usuarioServico.cadastrarUsuario(usuarioEmailDuplicado));
		}
		
		@Test
		@DisplayName("Verificando duplicidade de CPF/CNPJ.")
		void naoDeveCadastrarUsuarioPorDuplicidadeCPFCNPJ() {

			// Arrange
			Usuario input = new UsuarioComum();
			input.setCpfCnpj("11111111111");
			input.setEmail("maria@email.com");
			input.setId(1L);
			input.setNomeCompleto("Maria da Silva");
			input.setSaldo(new BigDecimal("1000.00"));
			input.setSenha("123456");
			
			Optional <Usuario> optUser = Optional.of(input);
	        when(usuarioRepositorio.findByCpfCnpj("11111111111")).thenReturn(optUser);
			doReturn(input).when(usuarioRepositorio).save(any());
			
			Usuario usuarioEmailDuplicado = new UsuarioComum();
			usuarioEmailDuplicado.setCpfCnpj("11111111111");
			usuarioEmailDuplicado.setEmail("jonatas@email.com");
			usuarioEmailDuplicado.setId(1L);
			usuarioEmailDuplicado.setNomeCompleto("Jonatas Oliveira");
			usuarioEmailDuplicado.setSaldo(new BigDecimal("1000.00"));
			usuarioEmailDuplicado.setSenha("123456");
			
			// Act e Assert - Retornando exceção correta em  caso de e-mail existente
			assertThrows(CpfCnpjExistenteExcecao.class,() -> usuarioServico.cadastrarUsuario(usuarioEmailDuplicado));
		}

	}

}
