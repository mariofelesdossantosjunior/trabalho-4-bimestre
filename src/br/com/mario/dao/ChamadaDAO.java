/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.model.Chamada;
import br.com.mario.model.ItemChamada;
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
public class ChamadaDAO implements Dao<Integer, Chamada> {

    protected Connection con;

    public ChamadaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Chamada entity) {
        String sql = "INSERT INTO chamada (data) "
                + "VALUES (?)";

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setDate(1, entity.getData());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }

            List<ItemChamada> itemChamadas = entity.getItemChamadas();
            ItemChamadaDAO dao = new ItemChamadaDAO(con);
            itemChamadas.stream()
                    .filter((itemChamada) -> (itemChamada.getId() == 0))
                    .forEach((itemChamada) -> {
                        dao.create(itemChamada);
                    });

            query.close();
            return true;

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
            return false;
        }
    }

    @Override
    public Chamada retrieve(Integer pk) {
        // Cria novo objeto
        Chamada chamada = null;

        // Define SQL
        String sql = "SELECT id, data FROM chamada WHERE id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                chamada = new Chamada();
                chamada.setId(rs.getInt("id"));
                chamada.setData(rs.getDate("data"));

                ItemChamadaDAO itemChamadaDAO = new ItemChamadaDAO(con);
                List<ItemChamada> itemChamadas = itemChamadaDAO.findByChamada(chamada.getId());

                for (ItemChamada itemChamada : itemChamadas) {
                    itemChamada.setChamada(chamada);
                }
                chamada.setItemChamadas(itemChamadas);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return chamada;
    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM chamada WHERE id = ?";

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
    public List<Chamada> findAll() {
        List<Chamada> chamadas = new ArrayList<>();

        // Define SQL
        String sql = "SELECT id, data FROM chamada";

        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);

            // Recupera dados do conjunto
            while (rs.next()) {
                Chamada chamada = new Chamada();
                chamada.setId(rs.getInt("id"));
                chamada.setData(rs.getDate("data"));

                ItemChamadaDAO itemChamadaDAO = new ItemChamadaDAO(con);
                List<ItemChamada> itemChamadas = itemChamadaDAO.findByChamada(chamada.getId());

                itemChamadas.stream()
                        .forEach((itemChamada) -> {
                            itemChamada.setChamada(chamada);
                        });

                chamada.setItemChamadas(itemChamadas);

                chamadas.add(chamada);
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return chamadas;
    }

    @Override
    public boolean update(Chamada entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
