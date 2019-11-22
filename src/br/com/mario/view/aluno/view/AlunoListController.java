/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.aluno.view;

import br.com.mario.dao.AlunoDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.model.Aluno;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class AlunoListController {

    private final AlunoListView view;
    private final AlunoDAO dao;

    public AlunoListController(AlunoListView view) {
        this.view = view;
        this.dao = new DaoFactory().alunoDao();
    }

    public void loadAlunos() {
        List<Aluno> alunos = dao.findAll();
        view.getTurmaTableModel().setListData(alunos);
    }

    public void remove(Aluno aluno) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            dao.delete(aluno.getId());
        }
    }

}
