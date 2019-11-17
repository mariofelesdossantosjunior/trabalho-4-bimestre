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
public class InstrutorDAO implements Dao<Integer, Instrutor> {

    protected Connection con;

    public InstrutorDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Instrutor entity) {
        String sql = "INSERT INTO instrutor (rg, nome, nascimento, titulacao) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getRg());
            query.setString(2, entity.getNome());
            query.setDate(3, entity.getNascimento());
            query.setString(4, entity.getTitulacao());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }

            List<Telefone> telefones = entity.getTelefones();
            TelefoneDAO telefoneDAO = new TelefoneDAO(con);
            
            System.out.println(telefones.toString());

            telefones.stream()
                    .peek(System.out::println)
                    .filter((telefone) -> (telefone.getId() == 0))
                    .forEach((telefone) -> {
                        telefoneDAO.create(telefone);
                    });

            /*List<Turma> turmas = entity.getTurmas();
             TelefoneDAO telefoneDAO = new TelefoneDAO(con);

             turmas.stream()
             .filter((telefone) -> (telefone.getId() == 0))
             .forEach((telefone) -> {
             telefoneDAO.create(telefone);
             });*/
            query.close();

            return true;

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
            return false;
        }
    }

    @Override
    public Instrutor retrieve(Integer pk) {
        // Cria novo objeto
        Instrutor instrutor = null;

        // Define SQL
        String sql = "SELECT id, rg, nome, nascimento, titulacao FROM instrutor WHERE id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);
            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                instrutor = new Instrutor();
                instrutor.setId(rs.getInt("id"));
                instrutor.setRg(rs.getString("rg"));
                instrutor.setNome(rs.getString("nome"));
                instrutor.setNascimento(rs.getDate("nascimento"));
                instrutor.setTitulacao(rs.getString("titulacao"));

                TelefoneDAO telefoneDAO = new TelefoneDAO(con);
                List<Telefone> telefones = telefoneDAO.findByInstrutor(instrutor.getId());

                for (Telefone telefone : telefones) {
                    telefone.setInstrutor(instrutor);
                }

                instrutor.setTelefones(telefones);

            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return instrutor;
    }

    @Override
    public boolean update(Instrutor entity) {
        String sql = "UPDATE instrutor SET rg = ?, nome = ?, nascimento = ?, titulacao = ? WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getRg());
            query.setString(2, entity.getNome());
            query.setDate(3, entity.getNascimento());
            query.setString(4, entity.getTitulacao());
            query.setInt(5, entity.getId());

            query.executeUpdate();
            
            List<Telefone> telefones = entity.getTelefones();
            TelefoneDAO telefoneDAO = new TelefoneDAO(con);

            telefones.stream()
                    .filter((telefone) -> (telefone.getId() == 0))
                    .forEach((telefone) -> {
                        telefoneDAO.create(telefone);
                    });
            
            query.close();

            return true;

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
            return false;
        }
    }

    public void delete(Instrutor instrutor) {
        TelefoneDAO telefoneDAO = new TelefoneDAO(con);

        instrutor.getTelefones().forEach(Telefone -> {

            if (Telefone.getId() != 0) {
                telefoneDAO.delete(Telefone.getId());
            }

        });

        String sql = "DELETE FROM instrutor WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, instrutor.getId());

            int rowsInserted = query.executeUpdate();
            query.close();

            System.out.println(rowsInserted);
        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM instrutor WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            int rowsInserted = query.executeUpdate();
            query.close();

            System.out.println(rowsInserted);
        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
    }

    @Override
    public List<Instrutor> findAll() {
        List<Instrutor> instrutors = new ArrayList<>();

        // Define SQL
        String sql = "SELECT id, rg, nome, nascimento, titulacao FROM instrutor";

        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);

            // Recupera dados do conjunto
            while (rs.next()) {
                Instrutor instrutor = new Instrutor();
                instrutor.setId(rs.getInt("id"));
                instrutor.setRg(rs.getString("rg"));
                instrutor.setNome(rs.getString("nome"));
                instrutor.setNascimento(rs.getDate("nascimento"));
                instrutor.setTitulacao(rs.getString("titulacao"));
                instrutors.add(instrutor);

                TelefoneDAO telefoneDAO = new TelefoneDAO(con);
                List<Telefone> telefones = telefoneDAO.findByInstrutor(instrutor.getId());

                telefones.stream().forEach((telefone) -> {
                    telefone.setInstrutor(instrutor);
                });

                instrutor.setTelefones(telefones);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return instrutors;
    }

}
