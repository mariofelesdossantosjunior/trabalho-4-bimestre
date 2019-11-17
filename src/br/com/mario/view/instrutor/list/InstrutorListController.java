/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.instrutor.list;

import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.InstrutorDAO;
import br.com.mario.model.Instrutor;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class InstrutorListController {

    private final InstrutorListView view;
    private final InstrutorDAO dao;

    public InstrutorListController(InstrutorListView view) {
        this.view = view;
        this.dao = new DaoFactory().instrutorDao();
    }

    public void loadInstrutores() {
        List<Instrutor> instrutors = dao.findAll();
        view.getInstrutorTableModel().setListData(instrutors);
    }

    public void remove(Instrutor instrutor) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            if (instrutor.getTelefones().isEmpty() && instrutor.getTurmas().isEmpty()) {
                dao.delete(instrutor.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Este registro contém dependências, \n por esse motivo não é possível sua remoção!");
            }
        }
    }

}
