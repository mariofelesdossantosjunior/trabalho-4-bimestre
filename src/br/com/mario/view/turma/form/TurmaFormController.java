/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.turma.form;

import br.com.mario.dao.AtividadeDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.InstrutorDAO;
import br.com.mario.dao.TurmaDAO;
import br.com.mario.model.Atividade;
import br.com.mario.model.Instrutor;
import br.com.mario.model.Turma;
import static br.com.mario.util.Util.convertToDate;
import static br.com.mario.util.Util.convertToString;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class TurmaFormController {

    private final TurmaFormView view;
    private final TurmaDAO turmaDAO;
    private final InstrutorDAO instrutorDAO;
    private final AtividadeDAO atividadeDAO;
    private Turma turma;

    public TurmaFormController(TurmaFormView view) {
        this.view = view;
        this.turmaDAO = new DaoFactory().turmaDao();
        this.instrutorDAO = new DaoFactory().instrutorDao();
        this.atividadeDAO = new DaoFactory().atividadeDao();
    }

    public boolean merge() {
        if (turma != null && turma.getId() != 0) {
            setValues();
            return turmaDAO.update(turma);

        } else {
            turma = new Turma();
            setValues();
            return turmaDAO.create(turma);
        }
    }

    private void setValues() throws NumberFormatException {
        turma.setNome(view.getTfNome().getText());
        turma.setHorario(view.getTfHorario().getText());
        turma.setDuracao(Float.parseFloat(view.getTfDuracao().getText()));
        turma.setDataInicio(convertToDate(view.getTfDataInicio().getText()));
        turma.setDataFim(convertToDate(view.getTfDataFim().getText()));
        turma.setAtividade(view.getAtividadeModel().getSelectedItem());
        turma.setInstrutor(view.getInstrutorModel().getSelectedItem());
    }

    public void edit(Turma turma) {
        this.turma = turma;

        view.getTfNome().setText(turma.getNome());
        view.getTfHorario().setText(turma.getHorario());
        view.getTfDuracao().setText(turma.getDuracao().toString());
        view.getTfDataInicio().setText(convertToString(turma.getDataInicio()));
        view.getTfDataFim().setText(convertToString(turma.getDataFim()));
        view.getAtividadeModel().setSelectedItem(turma.getAtividade());
        view.getInstrutorModel().setSelectedItem(turma.getInstrutor());
    }

    void remove(Turma turma) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            turmaDAO.delete(turma.getId());
        }
    }

    void loadInstrutores() {
        List<Instrutor> instrutors = instrutorDAO.findAll();
        view.getInstrutorModel().addListElements(instrutors);
    }

    void loadAtividades() {
        List<Atividade> atividades = atividadeDAO.findAll();
        view.getAtividadeModel().addListElements(atividades);
    }

}
