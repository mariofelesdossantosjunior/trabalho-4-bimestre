/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.chamada.form;

import br.com.mario.dao.ChamadaDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.MatriculaDAO;
import br.com.mario.dao.TurmaDAO;
import br.com.mario.model.Chamada;
import br.com.mario.model.ItemChamada;
import br.com.mario.model.Matricula;
import br.com.mario.model.Turma;
import br.com.mario.util.Util;
import static br.com.mario.util.Util.convertToString;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mario
 */
public class ChamadaFormController {

    private final ChamadaFormView view;
    private final ChamadaDAO chamadaDAO;
    private final TurmaDAO turmaDAO;
    private final MatriculaDAO matriculaDAO;
    private Chamada chamada;

    public ChamadaFormController(ChamadaFormView view) {
        this.view = view;
        this.chamadaDAO = new DaoFactory().chamadaDao();
        this.turmaDAO = new DaoFactory().turmaDao();
        this.matriculaDAO = new DaoFactory().matriculaDao();
    }

    public boolean merge() {
        chamada = new Chamada();
        chamada.setData(Util.convertToDate(view.getTfData().getText()));

        List<ItemChamada> itemChamadas = view.getTableModelMatricula().getRows()
                .stream()
                .map(item -> new ItemChamada(item.getStatus(), item, chamada))
                .collect(Collectors.toList());

        chamada.setItemChamadas(itemChamadas);
        return chamadaDAO.create(chamada);
    }

    void loadTurmas() {
        List<Turma> turmas = turmaDAO.findAll();
        view.getTurmaModel().addListElements(turmas);
    }

    public void reloadAlunos() {
        Turma turmaSelect = view.getTurmaModel().getSelectedItem();

        List<Matricula> matriculas = matriculaDAO.findAll().stream()
                .filter(matricula -> matricula.getTurma().getId() == turmaSelect.getId())
                .collect(Collectors.toList());

        view.getTableModelMatricula().setListData(matriculas);
    }

    public void edit(Chamada chamada) {
        this.chamada = chamada;
        
        statusComponents(false);
        
    
        view.getTfData().setText(convertToString(chamada.getData()));
        view.getTurmaModel().setSelectedItem(getTurmaSelect(chamada));
        view.getTableModelMatricula().setListData(getMatriculasChamada(chamada));
        
        view.getBtCancelar().setText("Voltar");
        view.getBtSalvar().setVisible(false);
    }

    private Turma getTurmaSelect(Chamada chamada) {
        return chamada.getItemChamadas().stream().findFirst().get().getMatricula().getTurma();
    }

    private List<Matricula> getMatriculasChamada(Chamada chamada) {
        return chamada.getItemChamadas()
                .stream()
                .map(item -> {
                    Matricula matricula = new Matricula();
                    matricula.setId(item.getMatricula().getId());
                    matricula.setAluno(item.getMatricula().getAluno());
                    matricula.setTurma(item.getMatricula().getTurma());
                    matricula.setDataMatricula(item.getMatricula().getDataMatricula());
                    matricula.setStatus(item.getPresente());
                    return matricula;
                })
                .collect(Collectors.toList());
    }

    private void statusComponents(boolean status) {
        view.getTfData().setEnabled(status);
        view.getCbTurma().setEnabled(status);
        view.getTbAlunos().setEnabled(status);
    }

}
