/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.matricula.form;

import br.com.mario.dao.AlunoDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.MatriculaDAO;
import br.com.mario.dao.TurmaDAO;
import br.com.mario.model.Aluno;
import br.com.mario.model.Matricula;
import br.com.mario.model.Turma;
import static br.com.mario.util.Util.convertToDate;
import static br.com.mario.util.Util.convertToString;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class MatriculaFormController {

    private final MatriculaFormView view;
    private final MatriculaDAO matriculaDAO;
    private final TurmaDAO turmaDAO;
    private final AlunoDAO alunoDAO;
    private Matricula matricula;

    public MatriculaFormController(MatriculaFormView view) {
        this.view = view;
        this.matriculaDAO = new DaoFactory().matriculaDao();
        this.alunoDAO = new DaoFactory().alunoDao();
        this.turmaDAO = new DaoFactory().turmaDao();
    }

    public boolean merge() {
        if (matricula != null) {
            setValues();
            return matriculaDAO.update(matricula);

        } else {
            matricula = new Matricula();
            setValues();
            return matriculaDAO.create(matricula);
        }
    }

    private void setValues() {
        matricula.setDataMatricula(convertToDate(view.getTfDataMatricula().getText()));
        matricula.setTurma(view.getTurmaModel().getSelectedItem());
        matricula.setAluno(view.getAlunoModel().getSelectedItem());
    }

    public void edit(Matricula matricula) {
        this.matricula = matricula;

        view.getTfDataMatricula().setText(convertToString(matricula.getDataMatricula()));
        view.getTurmaModel().setSelectedItem(matricula.getTurma());
        view.getAlunoModel().setSelectedItem(matricula.getAluno());
    }

    void remove(Aluno aluno) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            matriculaDAO.delete(aluno.getId());
        }
    }

    void loadTurmas() {
        List<Turma> turmas = turmaDAO.findAll();
        view.getTurmaModel().addListElements(turmas);
    }

    void loadAlunos() {
        List<Aluno> alunos = alunoDAO.findAll();
        view.getAlunoModel().addListElements(alunos);
    }

}
