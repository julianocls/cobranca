package com.algaworks.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Service
public class CadastroTituloService {

	@Autowired
	Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		} 
	}
	
	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}

	public List<Titulo> pesquisar(String descricao) {
		return titulos.findByDescricaoContaining(descricao);
	}

	public void receber(Long codigo) {
		Titulo titulo = titulos.findById(codigo).get();
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);		
	}
	
	
}
