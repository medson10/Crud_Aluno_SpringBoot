package com.example.projetoAluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAO {
	private Connection connection;

	public AlunoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Aluno aluno) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO aluno (nome, email, curso, semestre) VALUES (?, ?, ?, ?)");
		stmt.setString(1,aluno.getNome());
		stmt.setString(2,aluno.getEmail());
		stmt.setString(3,aluno.getCurso());
		stmt.setString(4,aluno.getSemestre());

		stmt.execute();
		stmt.close();
	}

	public List<Aluno> getLista() throws SQLException {

		List<Aluno> alunos = new ArrayList<Aluno>();

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Aluno aluno = new Aluno();

			aluno.setNome(rs.getString("nome"));
			aluno.setEmail(rs.getString("email"));
			aluno.setCurso(rs.getString("curso"));
			aluno.setSemestre(rs.getString("semestre"));

			alunos.add(aluno);

		}

		rs.close();
		stmt.close();

		return alunos;

	}

	public Aluno getAluno(String search) throws SQLException {

		Aluno aluno = new Aluno();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno WHERE " + "nome = ?");

			stmt.setString(1, search);
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
				aluno.setCurso(rs.getString("curso"));
				aluno.setEmail(rs.getString("semestre"));
			}
		}

	    catch (SQLException ex) {
	    	System.out.println(ex.toString());
	    }

		return (aluno);

	}


	public void excluir(String search) {

    try {

    	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM aluno WHERE nome = ?");
    	stmt.setString(1, search);
    	stmt.execute();

    }

	catch (SQLException ex) {
		System.out.println(ex.toString());
    }

  }


	public void altera(Aluno aluno, String nome) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE aluno SET nome=?, email=?, curso=?, semestre=? WHERE nome=?");

		stmt.setString(1, aluno.getNome());
		stmt.setString(2, aluno.getEmail());
		stmt.setString(3, aluno.getCurso());
		stmt.setString(4, aluno.getSemestre());
		stmt.setString(5, nome);

		stmt.execute();
		stmt.close();
	}
}
