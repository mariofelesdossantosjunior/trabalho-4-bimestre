/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.aluno.form;

import br.com.mario.dao.AlunoDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.TurmaDAO;
import br.com.mario.model.Aluno;
import br.com.mario.model.Turma;
import static br.com.mario.util.Util.convertToDate;
import static br.com.mario.util.Util.convertToString;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class AlunoFormController {

    private final AlunoFormView view;
    private final AlunoDAO alunoDAO;
    private final TurmaDAO turmaDAO;
    private Aluno aluno;

    public AlunoFormController(AlunoFormView view) {
        this.view = view;
        this.alunoDAO = new DaoFactory().alunoDao();
        this.turmaDAO = new DaoFactory().turmaDao();
    }

    public boolean merge() {
        if (aluno != null && aluno.getId() != 0) {
            setValues();
            return alunoDAO.update(aluno);

        } else {
            aluno = new Aluno();
            setValues();
            return alunoDAO.create(aluno);
        }
    }

    private void setValues() throws NumberFormatException {
        aluno.setNome(view.getTfNome().getText());
        aluno.setEndereco(view.getTfEndereco().getText());
        aluno.setTelefone(view.getTfTelefone().getText());
        aluno.setNascimento(convertToDate(view.getTfNascimento().getText()));
        aluno.setAltura(Float.parseFloat(view.getTfAltura().getText()));
        aluno.setPeso(Float.parseFloat(view.getTfPeso().getText()));
        aluno.setTurma(view.getTurmaModel().getSelectedItem());
    }

    public void edit(Aluno aluno) {
        this.aluno = aluno;

        view.getTfNome().setText(aluno.getNome());
        view.getTfEndereco().setText(aluno.getEndereco());
        view.getTfTelefone().setText(aluno.getTelefone());
        view.getTfNascimento().setText(convertToString(aluno.getNascimento()));
        view.getTfAltura().setText(aluno.getAltura().toString());
        view.getTfPeso().setText(aluno.getPeso().toString());
        view.getTurmaModel().setSelectedItem(aluno.getTurma());
    }

    void remove(Aluno aluno) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            alunoDAO.delete(aluno.getId());
        }
    }

    void loadTurmas() {
        List<Turma> turmas = turmaDAO.findAll();
        view.getTurmaModel().addListElements(turmas);
    }

}
