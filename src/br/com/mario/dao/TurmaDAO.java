/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.model.Atividade;
import br.com.mario.model.Instrutor;
import br.com.mario.model.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baro
 */
public class TurmaDAO implements Dao<Integer, Turma> {

    protected Connection con;

    public TurmaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Turma entity) {
        String sql = "INSERT INTO turma (nome, horario, duracao, datainicio, datafim, instrutor_id, atividade_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        if (entity.getInstrutor().getId() == 0) {
            InstrutorDAO dao = new InstrutorDAO(con);
            dao.create(entity.getInstrutor());
        }

        if (entity.getAtividade().getId() == 0) {
            AtividadeDAO dao = new AtividadeDAO(con);
            dao.create(entity.getAtividade());
        }

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getNome());
            query.setString(2, entity.getHorario());
            query.setFloat(3, entity.getDuracao());
            query.setDate(4, entity.getDataInicio());
            query.setDate(5, entity.getDataFim());

            query.setInt(6, entity.getInstrutor().getId());
            query.setInt(7, entity.getAtividade().getId());

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
    public Turma retrieve(Integer pk) {
        // Cria novo objeto
        Turma turma = null;

        // Define SQL
        String sql = "SELECT id, nome, horario, duracao, datainicio, datafim, instrutor_id, atividade_id FROM turma WHERE id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                turma = new Turma();
                turma.setNome(rs.getString("nome"));
                turma.setId(rs.getInt("id"));
                turma.setHorario(rs.getString("horario"));
                turma.setDuracao(rs.getFloat("duracao"));
                turma.setDataInicio(rs.getDate("datainicio"));
                turma.setDataFim(rs.getDate("datafim"));

                InstrutorDAO instrutorDAO = new InstrutorDAO(con);
                Instrutor instrutor = instrutorDAO.retrieve(rs.getInt("instrutor_id"));
                turma.setInstrutor(instrutor);

                AtividadeDAO atividadeDAO = new AtividadeDAO(con);
                Atividade atividade = atividadeDAO.retrieve(rs.getInt("atividade_id"));
                turma.setAtividade(atividade);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return turma;
    }

    @Override
    public boolean update(Turma entity) {
        String sql = "UPDATE turma SET nome = ?, horario = ?, duracao = ?, datainicio = ?, datafim = ?, instrutor_id = ?, atividade_id = ? WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getNome());
            query.setString(2, entity.getHorario());
            query.setFloat(3, entity.getDuracao());
            query.setDate(4, entity.getDataInicio());
            query.setDate(5, entity.getDataFim());
            query.setInt(6, entity.getInstrutor().getId());
            query.setInt(7, entity.getAtividade().getId());
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
        String sql = "DELETE FROM turma WHERE id = ?";

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
    public List<Turma> findAll() {
        List<Turma> turmas = new ArrayList<>();

        // Define SQL
        String sql = "SELECT nome, id, horario, duracao, datainicio, datafim, instrutor_id, atividade_id  FROM turma";

        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);

            // Recupera dados do conjunto
            while (rs.next()) {
                Turma turma = new Turma();
                turma.setNome(rs.getString("nome"));
                turma.setId(rs.getInt("id"));
                turma.setHorario(rs.getString("horario"));
                turma.setDuracao(rs.getFloat("duracao"));
                turma.setDataInicio(rs.getDate("datainicio"));
                turma.setDataFim(rs.getDate("datafim"));

                InstrutorDAO instrutorDAO = new InstrutorDAO(con);
                Instrutor instrutor = instrutorDAO.retrieve(rs.getInt("instrutor_id"));
                turma.setInstrutor(instrutor);

                AtividadeDAO atividadeDAO = new AtividadeDAO(con);
                Atividade atividade = atividadeDAO.retrieve(rs.getInt("atividade_id"));
                turma.setAtividade(atividade);
                
                turmas.add(turma);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return turmas;
    }

}
