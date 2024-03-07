package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repositroy.PostagemRepository;

@RestController
//Recebe requisições compostas por : 
//URL : Endereço da nossa requisição,nesse caso o "/hello-world"
//Verbo : Qual método HTTP será acionado , nesse caso o @GetMapping
//Corpo da requisição ( Request Body): Objeto que contem os dados que serão atualizados ou criados , nesse caso o return "hello word"
//Após receber e processar a requisição,a classe Controladora responde com :
//	Um código de status HTTP
//O resultado desse processamento (Dados de uma consulta, por exemplo) inserido diretamente no corpo da resposta (Response Body)

@RequestMapping("/postagens")
//É para mapear solicitações para os métodos da classe controloadora, definir a URL (endereço) padrão do recurso (em nosso exemplo: /hello-world). 
//Ao digitar a url do servidor seguida da url do recurso, o Spring envia a requisição para a Classe responsável pelo recurso com este endereço.
//Exemplo: http://localhost:8080/hello-world é o endereço do recurso hello-world da Classe Controladora HelloWorldController.

@CrossOrigin(origins = "*", allowedHeaders = " ")
//indica que a Classe controladora permitirá o recebimento de requisições realizadas de fora do domínio (localhost e futuramente da nuvem quando o
//Deploy da aplicação for efetivado) ao qual ela pertence. Essa anotação é essencial para que o front-end ou aplicativo mobile, tenha acesso aos
//Métodos e Recursos da nossa aplicação (O termo técnico é consumir a API).
//Além de liberar todas as Origens das requisições (parâmetro origins), a anotação libera também os Cabeçalhos das Requisições (parâmetro allowedHeaders), 
//que em alguns casos trazem informações essenciais para o correto funcionamento da aplicação. Um bom exemplo é o Token de Autorização - Authorization 
//Token, que veremos mais à frente, que tem a função de autorizar o acesso à um endpoint específico, protegido pela autenticação (login). Em produção, 
//recomenda-se substituir o * (asterisco) pelo endereço na nuvem (deploy) do Frontend ou da aplicação mobile.
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping
	// Informando que o método GetAll abaixo irá retornar uma requisição GET
	public ResponseEntity<List<Postagem>> getAll() {
		// O get All quer dizer que ele vai responder toda requisição do tipo HTPP GET
		// que for pro endereco : http://localhost:8080/postagens/.

		// ATENÇÃO: O Endereço do endpoint será igual ao Endereço do Recurso
		// (@RequestMapping) apenas quando a anotação @GetMapping não possuir um
		// endereço personalizado, como um parâmetro por exemplo. Caso existam dois ou
		// mais Métodos do tipo GET será necessário personalizar o
		// endereço de cada Método anotado com @GetMapping, pois não pode existir
		// endereços duplicados.

		// O método getAll é do tipo responseEntity porque ele vai responder a
		// requisição http com uma resposta http
		// Esse método terá como retorno um objeto da classe ResponseEntity e no
		// parametro body será um objeto da classe List
		return ResponseEntity.ok(postagemRepository.findAll());
		// Mas por que eu não chamei a lista na hora de fazer o return?
		// A razão para não chamar diretamente a lista no retorno do método está
		// relacionada ao encapsulamento da resposta HTTP.
		// A classe ResponseEntity fornece um meio de incluir informações adicionais
		// sobre a resposta HTTP, como headers, status code,
		// etc. Ao encapsular a lista de postagens dentro de ResponseEntity.ok(...),
		// você está indicando explicitamente que a operação
		// foi bem-sucedida (código 200 OK) e está incluindo o corpo da resposta, que é
		// a lista de postagens.

		// Se você chamasse diretamente postagemRepository.findAll() sem envolvê-lo em
		// ResponseEntity.ok(...), estaria retornando apenas
		// a lista de postagens, sem qualquer informação adicional relacionada à
		// resposta HTTP. Isso pode ser apropriado em alguns casos,
		// mas usar ResponseEntity oferece maior flexibilidade ao lidar com diferentes
		// casos de resposta e permite definir cabeçalhos HTTP
		// e status personalizados, se necessário.

	}

	@GetMapping("/{id}")
	//Pedir o id(Variável de caminho / Path Variable) da postagem que quer ser puxada
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		//Dessa vez será retornada apenas uma postagem 
		//O PathVariable insere o valor que for passado no endereço do endpoint
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				//Se for encontrado alguma postagem o id informado,cai dentro do map(Optional)
				//dentro do resposta será mapeado o objeto que foi encontrado,e logo após retorna o objeto e também o Status HTTP(200)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				//Se não for encontrado,cai aqui dentro,será retornado o HTTP (404 - NOT FOUND) 
				//eu defini que o status dessa requisição será como not_found,quando eu preciso
				//definir o status da requisição,eu preciso usar o .build() para é necessário para finalizar a construção da instância e retorná-la
		

	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo)  {
		return ResponseEntity.ok(postagemRepository.findByTituloContainingIgnoreCase(titulo));
		//ATENÇÃO: Por que aqui a gente não fez um map ,else ? Todo método que tem como retorno 
		//uma lista,auomaticamente ela será gerada,então caso ele não encontre ainda assim será
		//retornado uma lista vazia
	}
}
