/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.matricula.view;

import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.MatriculaDAO;
import br.com.mario.model.Matricula;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class MatriculaListController {

    private final MatriculaListView view;
    private final MatriculaDAO dao;

    public MatriculaListController(MatriculaListView view) {
        this.view = view;
        this.dao = new DaoFactory().matriculaDao();
    }

    public void loadMatriculas() {
        List<Matricula> matriculas = dao.findAll();
        view.getMatriculaTableModel().setListData(matriculas);
    }

    public void remove(Matricula matricula) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            dao.delete(matricula.getId());
        }
    }
}
