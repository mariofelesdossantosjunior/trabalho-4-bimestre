/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.model.Chamada;
import br.com.mario.model.ItemChamada;
import br.com.mario.model.Matricula;
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
public class ItemChamadaDAO implements Dao<Integer, ItemChamada> {

    protected Connection con;

    public ItemChamadaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(ItemChamada entity) {
        String sql = "INSERT INTO item_chamada (presente, id_matricula, id_chamada) "
                + "VALUES (?, ?, ?)";

        if (entity.getMatricula().getId() == 0) {
            MatriculaDAO dao = new MatriculaDAO(con);
            dao.create(entity.getMatricula());
        }

        if (entity.getChamada().getId() == 0) {
            ChamadaDAO dao = new ChamadaDAO(con);
            dao.create(entity.getChamada());
        }

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setBoolean(1, entity.getPresente());
            query.setInt(2, entity.getMatricula().getId());
            query.setInt(3, entity.getChamada().getId());

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
    public ItemChamada retrieve(Integer pk) {
        // Cria novo objeto
        ItemChamada itemChamada = null;

        // Define SQL
        String sql = "SELECT id, presente, id_matricula, id_chamada FROM item_chamada WHERE id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                itemChamada = new ItemChamada();
                itemChamada.setId(rs.getInt("id"));
                itemChamada.setPresente(rs.getBoolean("presente"));

                MatriculaDAO matriculaDAO = new MatriculaDAO(con);
                Matricula matricula = matriculaDAO.retrieve(rs.getInt("id_matricula"));
                itemChamada.setMatricula(matricula);

                ChamadaDAO chamadaDAO = new ChamadaDAO(con);
                Chamada chamada = chamadaDAO.retrieve(rs.getInt("id_chamada"));
                itemChamada.setChamada(chamada);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return itemChamada;
    }
    
    public List<ItemChamada> findByChamada(int idChamada){
        List<ItemChamada> itemChamadas = new ArrayList<>();

        // Define SQL
        String sql = "SELECT id, presente, id_matricula, id_chamada FROM item_chamada WHERE id_chamada = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, idChamada);
            
            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                ItemChamada itemChamada = new ItemChamada();
                itemChamada.setId(rs.getInt("id"));
                itemChamada.setPresente(rs.getBoolean("presente"));

                MatriculaDAO matriculaDAO = new MatriculaDAO(con);
                Matricula matricula = matriculaDAO.retrieve(rs.getInt("id_matricula"));
                itemChamada.setMatricula(matricula);

                itemChamadas.add(itemChamada);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return itemChamadas;
    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM item_chamada WHERE id = ?";

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
    public List<ItemChamada> findAll() {
        List<ItemChamada> itemChamadas = new ArrayList<>();

        // Define SQL
        String sql = "SELECT SELECT id, presente, id_matricula, id_chamada FROM item_chamada";

        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);

            // Recupera dados do conjunto
            while (rs.next()) {
                ItemChamada itemChamada = new ItemChamada();
                itemChamada.setId(rs.getInt("id"));
                itemChamada.setPresente(rs.getBoolean("presente"));

                MatriculaDAO matriculaDAO = new MatriculaDAO(con);
                Matricula matricula = matriculaDAO.retrieve(rs.getInt("id_matricula"));
                itemChamada.setMatricula(matricula);

                ChamadaDAO chamadaDAO = new ChamadaDAO(con);
                Chamada chamada = chamadaDAO.retrieve(rs.getInt("id_chamada"));
                itemChamada.setChamada(chamada);

                itemChamadas.add(itemChamada);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return itemChamadas;
    }

    @Override
    public boolean update(ItemChamada entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
