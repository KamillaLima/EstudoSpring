package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_temas")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "DEVE SER INFORMADO UMA DESCRIÇÃO")
	// Não deixa o atributo ser nulo,porém permite espaços em branco
	@Size(min = 10, max = 100)
	private String descricao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tema", cascade = CascadeType.REMOVE)
	/*
	 * Um tema tem várias postagens Fetch : Estragégia de busca e carregamento de
	 * dados,LAZY = PREGUIÇOSA e EAGER = ANSIOSA usando a LAZY ela não carregará os
	 * dados do Tema associado a cada Postagem até que os dados sejam solicitados.
	 * 
	 * mappedby : Ela está mapeando o relacionamento,já que é a mãe e a postagem a
	 * filha
	 * 
	 * cascade: Quandouma ação na entidade destino for feita (tema),a mesma ação é
	 * feita na entidade associada (postagem)
	 *
	 * REMOVE: Quando um objeto da classe tema é apagado,TODAS as postagens com esse
	 * tema também serão removidas. ATENÇÃO: SE UMA POSTAGEM FOR REMOVIDA O TEMA NÃO
	 * SERÁ!!!!
	 */
	@JsonIgnoreProperties("tema")
	// Pra impedir o looping infinito
	private List<Postagem> postagem;

	public Long getId() {
		return id;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
