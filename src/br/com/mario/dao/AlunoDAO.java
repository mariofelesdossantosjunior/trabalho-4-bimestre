/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.model.Aluno;
import br.com.mario.model.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baro
 */
public class AlunoDAO implements Dao<Integer, Aluno> {

    protected Connection con;

    public AlunoDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Aluno entity) {
        String sql = "INSERT INTO aluno (nome, endereco, telefone, nascimento, altura, peso, turma_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getNome());
            query.setString(2, entity.getEndereco());
            query.setString(3, entity.getTelefone());
            query.setDate(4, entity.getNascimento());
            query.setFloat(5, entity.getAltura());
            query.setFloat(6, entity.getPeso());

            if (entity.getTurma() != null) {
                query.setInt(7, entity.getTurma().getId());
            } else {
                query.setNull(7, Types.INTEGER);
            }

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }

            query.close();
            return true;

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
            return false;
        }
    }

    @Override
    public Aluno retrieve(Integer pk) {
        // Cria novo objeto
        Aluno aluno = null;

        // Define SQL
        String sql = "SELECT id, nome, endereco, telefone, nascimento, altura, peso, turma_id FROM aluno WHERE id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setNascimento(rs.getDate("nascimento"));
                aluno.setAltura(rs.getFloat("altura"));
                aluno.setPeso(rs.getFloat("peso"));

                TurmaDAO turmaDAO = new TurmaDAO(con);
                Turma turma = turmaDAO.retrieve(rs.getInt("turma_id"));
                aluno.setTurma(turma);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return aluno;
    }

    @Override
    public boolean update(Aluno entity) {
        String sql = "UPDATE aluno SET nome = ?, endereco = ?, telefone = ?, nascimento = ?, altura = ?, peso = ?, turma_id = ? FROM aluno WHERE id = ? ";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getNome());
            query.setString(2, entity.getEndereco());
            query.setString(3, entity.getTelefone());
            query.setDate(4, entity.getNascimento());
            query.setFloat(5, entity.getAltura());
            query.setFloat(6, entity.getPeso());
            query.setInt(7, entity.getTurma().getId());
            query.setInt(8, entity.getId());

            query.executeUpdate();

            query.close();

            return true;

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
            return false;
        }
    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            query.executeUpdate();
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
    }

    @Override
    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();

        // Define SQL
        String sql = "SELECT id, nome, endereco, telefone, nascimento, altura, peso, turma_id FROM aluno";

        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);

            // Recupera dados do conjunto
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setNascimento(rs.getDate("nascimento"));
                aluno.setAltura(rs.getFloat("altura"));
                aluno.setPeso(rs.getFloat("peso"));

                TurmaDAO turmaDAO = new TurmaDAO(con);
                Turma turma = turmaDAO.retrieve(rs.getInt("turma_id"));
                aluno.setTurma(turma);

                alunos.add(aluno);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return alunos;
    }

}
