package com.generation.blogpessoal.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
//Classe que pode injetar e instanciar qualquer dependencia mencionada nessa classe

/**
 * @Component: é uma anotação genérica para qualquer componente gerenciado pelo
 *             Spring. O Spring fornece algumas anotações especializadas,
 *             como: @Controller e @Service. Todas elas fornecem a mesma função
 *             que a anotação @Component. Todas elas agem da mesma forma, porque
 *             são anotações compostas internamente pela anotação @Component.
 *             Elas são como "alias" (apelido) para a anotação @Component,
 *             entretanto cada uma possui usos especializados e específicos.
 * 
 *             O grande diferencial desta anotação é que ela é detectada
 *             automaticamente pelo Spring, ou seja, não é necessário instanciar
 *             um Objeto ou indicar um uso específico (Service, Controller,
 *             Repository, entre outros).
 */
public class JwtService {
	/**
	 * Responsável por criar e validar o TokenJWT(ele é criado durante o processo de
	 * autenticação(login) do usuário e será validado em TODAS as requisições HTTP
	 * enviadas para os endpoints protegidos(lá na classe BasicSecurityConfig)
	 */
	
	public static final String SECRET = "c2cb2ea1862bdc8916f9545784bc1a04f5498d286d3e141fd360bd65b5afb99a";
	//Está armazenando a chave de assinatura do Token JWT,foi definido como final porque ele NUNCA se alterará.
	//Foi definido como static porque deve estar associado apenas a essa classe,é uma variavel da classe e nao do objeto
	//Para gerar uma , entrar no site https://www.keygen.io/#fakeLink e ir na opção SHA 256-bit Key
	
	private Key getSignKey() {
		//Método responsável por pegar a nossa chave gerada acima e co
		//PAREI AQUI
		byte[] keyBytes = Decoders.BASE64.decode(SECRET)
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
