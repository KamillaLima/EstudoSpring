package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.blogpessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long>{
	 public List<Tema> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	 //Criando um método que irá retornar uma lista 
	 //Param = Define a variável String descricao como um parâmetro da consulta. Esta anotação é obrigatório em consultas do tipo Like.
	 //String descricao = Parâmetro do Método contendo a descrição que você deseja procurar.
}
