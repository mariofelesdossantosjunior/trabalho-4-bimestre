/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.chamada.view;

import br.com.mario.dao.ChamadaDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.model.Chamada;
import br.com.mario.model.Matricula;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class ChamadaListController {

    private final ChamadaListView view;
    private final ChamadaDAO dao;

    public ChamadaListController(ChamadaListView view) {
        this.view = view;
        this.dao = new DaoFactory().chamadaDao();
    }

    public void loadChamadas() {
        List<Chamada> chamadas = dao.findAll();
        view.getChamadaTableModel().setListData(chamadas);
    }

    public void remove(Matricula matricula) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            dao.delete(matricula.getId());
        }
    }
}
