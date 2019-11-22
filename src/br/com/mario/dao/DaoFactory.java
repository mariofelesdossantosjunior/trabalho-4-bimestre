/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import br.com.mario.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class DaoFactory {

    private static Connection connection;

    public DaoFactory() {
        try {
            connection = ConnectionFactory.createConnectionToMySQL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Verifique sua conexão com banco de dados!");
            ex.printStackTrace();
        }
    }

    public AtividadeDAO atividadeDao() {
        return new AtividadeDAO(connection);
    }

    public InstrutorDAO instrutorDao() {
        return new InstrutorDAO(connection);
    }

    public TelefoneDAO telefoneDao() {
        return new TelefoneDAO(connection);
    }

    public TurmaDAO turmaDao() {
        return new TurmaDAO(connection);
    }

    public AlunoDAO alunoDao() {
        return new AlunoDAO(connection);
    }

}
