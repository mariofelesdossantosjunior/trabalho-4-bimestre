/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.model.Instrutor;
import br.com.mario.model.Telefone;
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
public class TelefoneDAO implements Dao<Integer, Telefone> {

    protected Connection con;

    public TelefoneDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Telefone entity) {
        String sql = "INSERT INTO telefone (numero, tipo, instrutor_id) "
                + "VALUES (?, ?, ?)";

        if (entity.getInstrutor().getId() == 0) {
            InstrutorDAO dao = new InstrutorDAO(con);
            dao.create(entity.getInstrutor());
        }

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getNumero());
            query.setString(2, entity.getTipo());
            query.setInt(3, entity.getInstrutor().getId());

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
    public Telefone retrieve(Integer pk) {
        // Cria novo objeto
        Telefone telefone = null;

        // Define SQL
        String sql = "SELECT id, numero, tipo, instrutor_id FROM telefone WHERE id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(rs.getString("tipo"));
                // Cada produto tem uma categoria.
                InstrutorDAO dao = new InstrutorDAO(con);
                Instrutor instrutor = dao.retrieve(rs.getInt("instrutor_id"));
                telefone.setInstrutor(instrutor);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return telefone;
    }

    @Override
    public boolean update(Telefone entity) {
        String sql = "UPDATE telefone SET numero = ?, tipo = ?,  instrutor_id = ? WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getNumero());
            query.setString(2, entity.getTipo());
            query.setInt(3, entity.getInstrutor().getId());
            query.setInt(4, entity.getId());

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
        String sql = "DELETE FROM telefone WHERE id = ?";

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
    public List<Telefone> findAll() {
        List<Telefone> telefones = new ArrayList<>();

        // Define SQL
        String sql = "SELECT id, numero, tipo, instrutor_id FROM telefone";

        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);

            // Recupera dados do conjunto
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(rs.getString("tipo"));
                
                
                InstrutorDAO dao = new InstrutorDAO(con);
                Instrutor instrutor = dao.retrieve(rs.getInt("instrutor_id"));
                telefone.setInstrutor(instrutor);
                telefones.add(telefone);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return telefones;
    }

    public List<Telefone> findByInstrutor(int pkInstrutor) {
        List<Telefone> telefones = new ArrayList<>();

        // Define SQL
        String sql = "SELECT id, numero, tipo, instrutor_id FROM telefone where instrutor_id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pkInstrutor);

            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setTipo(rs.getString("tipo"));
                telefones.add(telefone);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return telefones;
    }

}
