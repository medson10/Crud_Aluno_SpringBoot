package com.example.projetoAluno;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class AlunoController {
	
	private Map<Integer, Aluno> alunos;
	private AlunoDAO alunoDao = new AlunoDAO();

	public AlunoController() throws SQLException {
	  alunos = new HashMap<Integer, Aluno>();
	}

	@CrossOrigin
	@RequestMapping(value = "/api/aluno", method = RequestMethod.GET)
	public ResponseEntity<List<Aluno>> listar() throws SQLException {
		int index=0;

		List<Aluno> alunosGetted = new ArrayList<Aluno>();
		alunos = new HashMap<Integer, Aluno>();

		alunosGetted = alunoDao.getLista();

		for (Aluno a : alunosGetted) {
			alunos.put(index, a);
			index++;
		}

		return new ResponseEntity<List<Aluno>>(new ArrayList<Aluno>(alunos.values()), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/api/aluno/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> buscar(@PathVariable("nome") String nome) throws SQLException {

	  Aluno aluno = alunoDao.getAluno(nome);
	  if (aluno == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/api/aluno/{nome}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("nome") String nome) {
		alunoDao.excluir(nome);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@CrossOrigin
	@RequestMapping(value = "/api/aluno", method = RequestMethod.POST)
	public ResponseEntity<Aluno> addAluno(@RequestBody Aluno aluno) throws JsonParseException, JsonMappingException, IOException, SQLException {

		alunoDao.adiciona(aluno);
		return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/api/aluno/{nome}", method = RequestMethod.PUT)
	public ResponseEntity<Aluno> updateAluno(@RequestBody Aluno aluno, @PathVariable("nome") String nome) throws JsonParseException, JsonMappingException, IOException, SQLException {

		alunoDao.altera(aluno, nome);
		return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
	}
}
