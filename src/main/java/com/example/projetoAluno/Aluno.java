package com.example.projetoAluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aluno {
	
    private String nome;
    private String email;
    private String curso;
    private String semestre;
    
    public Aluno() {}
    
    public Aluno(
    		@JsonProperty("nome")String nome, 
    		@JsonProperty("email")String email, 
    		@JsonProperty("curso")String curso,
    		@JsonProperty("semestre")String semestre){
    	
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.semestre = semestre;
    }


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}


	public String getSemestre() {
		return semestre;
	}


	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

    

}
