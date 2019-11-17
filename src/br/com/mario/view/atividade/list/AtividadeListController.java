/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.atividade.list;

import br.com.mario.dao.AtividadeDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.model.Atividade;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class AtividadeListController {

    private final AtividadeListView view;
    private final AtividadeDAO dao;

    public AtividadeListController(AtividadeListView view) {
        this.view = view;
        this.dao = new DaoFactory().atividadeDao();
    }

    public void loadAtividades() {
        List<Atividade> atividades = dao.findAll();
        view.getAtividadeTableModel().setListData(atividades);
    }

    public void remove(Atividade atividade) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            
            if (atividade.getTurmas().isEmpty()) {
                dao.delete(atividade.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Este registro contém dependências, \n por esse motivo não é possível sua remoção!");
            }
        }
    }
}
