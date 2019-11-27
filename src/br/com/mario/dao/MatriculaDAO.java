/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.model.Aluno;
import br.com.mario.model.Matricula;
import br.com.mario.model.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class MatriculaDAO implements Dao<Integer, Matricula> {

    protected Connection con;

    public MatriculaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Matricula entity) {
        String sql = "INSERT INTO matricula (aluno_id, turma_id, data_matricula) VALUES (?, ?, ?)";

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setInt(1, entity.getAluno().getId());
            query.setInt(2, entity.getTurma().getId());
            query.setDate(3, entity.getDataMatricula());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }

            query.close();
            return true;

        } catch (Exception e) {
            System.out.println("SQL exception ocorred " + e);
            return false;
        }

    }

    @Override
    public Matricula retrieve(Integer pk) {
        Matricula matricula = null;

        String sql = "SELECT id, aluno_id, turma_id, data_matricula FROM matricula WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            ResultSet rs = query.executeQuery();
            AlunoDAO alunoDAO = new AlunoDAO(con);
            TurmaDAO turmaDAO = new TurmaDAO(con);

            if (rs.next()) {
                matricula = new Matricula();
                matricula.setId(rs.getInt("id"));
                matricula.setDataMatricula(rs.getDate("data_matricula"));

                Aluno aluno = alunoDAO.retrieve(rs.getInt("aluno_id"));
                matricula.setAluno(aluno);

                Turma turma = turmaDAO.retrieve(rs.getInt("turma_id"));
                matricula.setTurma(turma);
            }

            query.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occured " + e);
        }

        return matricula;
    }

    @Override
    public boolean update(Matricula entity) {
        String sql = "UPDATE matricula SET turma_id = ?, aluno_id = ?, data_matricula = ? WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, entity.getTurma().getId());
            query.setInt(2, entity.getAluno().getId());
            query.setDate(3, entity.getDataMatricula());
            query.setInt(4, entity.getId());
            query.executeUpdate();

            query.close();
            return true;

        } catch (SQLException ex) {
            System.out.println("SQL Exception occured " + ex);
            return false;
        }

    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM matricula WHERE id = ?";
        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);
            query.executeUpdate();

            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL Exception occured " + ex);
        }
    }

    @Override
    public List<Matricula> findAll() {
        List<Matricula> matriculas = new LinkedList<>();

        String sql = "SELECT id, data_matricula, aluno_id, turma_id FROM matricula";
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(sql);

            TurmaDAO turmaDAO = new TurmaDAO(con);
            AlunoDAO alunoDAO = new AlunoDAO(con);

            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setId(rs.getInt("id"));
                matricula.setDataMatricula(rs.getDate("data_matricula"));

                Aluno aluno = alunoDAO.retrieve(rs.getInt("aluno_id"));
                matricula.setAluno(aluno);

                Turma turma = turmaDAO.retrieve(rs.getInt("turma_id"));
                matricula.setTurma(turma);

                matriculas.add(matricula);
            }

            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL Exception occured " + ex);
        }

        return matriculas;
    }

    public boolean validate(Matricula matricula) {
        String sql = "SELECT count(id) as existe FROM matricula WHERE aluno_id = ? AND turma_id = ?";
        boolean existe = false;

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, matricula.getAluno().getId());
            query.setInt(2, matricula.getTurma().getId());

            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                existe = rs.getInt("existe") > 0;
            }

            query.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occured " + e);
        }

        return existe;
    }

}
