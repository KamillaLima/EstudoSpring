package com.generation.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

@Service
//Se trata de uma classe de serviço,então ela é responsável pela implementação das regras de negócio
//e as tratativas de dados de uma parte do ou recurso do sistema
public class UserDetailsServiceImpl implements UserDetailsService {
	/**
	 * Responsável por validar a existência de um usuário no sistema(através do
	 * nosso banco de dados), e retornar um objeto da classe UserDetailsImpl,essa
	 * busca será feita através do email
	 */

	@Autowired
	/**
	 * cria um ponto de Injeção da Interface UsuarioRepository na Classe
	 * UserDetailsServiceImpl, permitindo acessar os Métodos de interação com os
	 * Objetos da Classe Usuario, persistidos no Banco de dados da aplicação.
	 */
	private UsuarioRepository usuarioRepository;
	// Já que estamos falando de procurar no banco,nada mais justo do que chamar a
	// interface do usuarioRepository

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//Esse método irá receber o usuário através da tela de login do sistema
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);

		if (usuario.isPresent())
			
			return new UserDetailsImpl(usuario.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			
	}

}
