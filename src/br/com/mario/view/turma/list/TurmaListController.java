/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.turma.list;

import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.TurmaDAO;
import br.com.mario.model.Turma;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class TurmaListController {

    private final TurmaListView view;
    private final TurmaDAO dao;

    public TurmaListController(TurmaListView view) {
        this.view = view;
        this.dao = new DaoFactory().turmaDao();
    }

    public void loadTurmas() {
        List<Turma> turmas = dao.findAll();
        view.getTurmaTableModel().setListData(turmas);
    }

    public void remove(Turma turma) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            if (turma.getAlunos() == null) {
                dao.delete(turma.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Este registro contém dependências, \n por esse motivo não é possível sua remoção!");
            }
        }
    }

}
