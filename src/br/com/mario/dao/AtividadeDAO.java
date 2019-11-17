/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.model.Atividade;
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
public class AtividadeDAO implements Dao<Integer, Atividade> {

    protected Connection con;

    public AtividadeDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Atividade entity) {
        String sql = "INSERT INTO atividade (nome) VALUES (?)";

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getNome());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                entity.setId(id);
            }

            query.close();
            return true;

        } catch (Exception e) {
            System.out.println("SQL exception ocorred " + e);
            return false;
        }
    }

    @Override
    public Atividade retrieve(Integer pk) {
        Atividade atividade = null;

        String sql = "SELECT id, nome FROM atividade WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                atividade = new Atividade();
                atividade.setId(rs.getInt("id"));
                atividade.setNome(rs.getString("nome"));
            }
            query.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occured " + e);
        }

        return atividade;
    }

    @Override
    public boolean update(Atividade entity) {
        String sql = "UPDATE atividade SET nome = ? WHERE id = ?";
        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getNome());
            query.setInt(2, entity.getId());
            query.executeUpdate();

            query.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM atividade WHERE id = ?";
        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);
            query.executeUpdate();

            query.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Atividade> findAll() {
        List<Atividade> atividades = new LinkedList<>();

        String sql = "SELECT * FROM atividade";
        try {
            Statement query = con.createStatement();
            ResultSet result = query.executeQuery(sql);

            while (result.next()) {
                Atividade atividade = new Atividade();
                atividade.setId(result.getInt("id"));
                atividade.setNome(result.getString("nome"));
                atividades.add(atividade);
            }

            query.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return atividades;
    }

}
