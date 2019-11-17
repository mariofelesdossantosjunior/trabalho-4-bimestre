/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.atividade.form;

import br.com.mario.dao.AtividadeDAO;
import br.com.mario.dao.DaoFactory;
import br.com.mario.model.Atividade;


/**
 *
 * @author mario
 */
public class AtividadeFormController {

    private final AtividadeFormView view;
    private final AtividadeDAO dao;
    private Atividade atividade;

    public AtividadeFormController(AtividadeFormView view) {
        this.view = view;
        this.dao = new DaoFactory().atividadeDao();
    }

    public boolean merge() {
        if (atividade != null) {
            atividade.setNome(view.getTfDescricao().getText());
            return dao.update(atividade);

        } else {
            atividade = new Atividade();
            atividade.setNome(view.getTfDescricao().getText());
            return dao.create(atividade);
        }
    }

    public void edit(Atividade atividade) {
        this.atividade = atividade;

        view.getTfDescricao().setText(atividade.getNome());
    }

}
