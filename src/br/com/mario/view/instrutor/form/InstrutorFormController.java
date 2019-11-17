/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.view.instrutor.form;

import br.com.mario.dao.DaoFactory;
import br.com.mario.dao.InstrutorDAO;
import br.com.mario.dao.TelefoneDAO;
import br.com.mario.model.Instrutor;
import br.com.mario.model.Telefone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class InstrutorFormController {

    private final InstrutorFormView view;
    private final InstrutorDAO instrutorDAO;
    private final TelefoneDAO telefoneDAO;
    private Instrutor instrutor;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public InstrutorFormController(InstrutorFormView view) {
        this.view = view;
        this.instrutorDAO = new DaoFactory().instrutorDao();
        this.telefoneDAO = new DaoFactory().telefoneDao();
    }

    public boolean merge() {
        if (instrutor != null && instrutor.getId() != 0) {
            setValues();
            return instrutorDAO.update(instrutor);

        } else {
            if (instrutor == null) {
                instrutor = new Instrutor();
            }
            setValues();
            return instrutorDAO.create(instrutor);

        }
    }

    private void setValues() {
        instrutor.setNome(view.getTfNome().getText());
        instrutor.setRg(view.getTfRg().getText());
        instrutor.setNascimento(convertDataNascimento());
        instrutor.setTitulacao(view.getTfTitulacao().getText());
        instrutor.setTelefones(view.getTelefoneTableModel().getRows());
    }

    public void edit(Instrutor instrutor) {
        this.instrutor = instrutor;

        view.getTfNome().setText(instrutor.getNome());
        view.getTfRg().setText(instrutor.getRg());
        view.getTfNascimento().setText(dateFormat.format(instrutor.getNascimento()));
        view.getTfTitulacao().setText(instrutor.getTitulacao());
        view.getTelefoneTableModel().setListData(instrutor.getTelefones());
    }

    public void editTelefone(Telefone telefone) {
        view.getTfNumeroTelefone().setText(telefone.getNumero());
        view.getTfTipoTelefone().setText(telefone.getTipo());
    }

    private java.sql.Date convertDataNascimento() {
        try {
            return new java.sql.Date(dateFormat.parse(view.getTfNascimento().getText()).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Função reponsavel por adicionar
     *
     * @param text
     * @param text0
     */
    void adicionarTelefone(String tipo, String numero) {
        if (instrutor == null) {
            instrutor = new Instrutor();
        }
        instrutor.getTelefones().add(new Telefone(numero, tipo, instrutor));
    }

    /**
     * Função responsavel por recarregar todos telefones
     */
    public void loadTelefones() {
        view.getTelefoneTableModel().setListData(instrutor.getTelefones());
    }

    void remove(Telefone telefone) {
        int status = JOptionPane.showConfirmDialog(null, "Desejá realmente remover?", "Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (status == JOptionPane.YES_OPTION) {
            telefoneDAO.delete(telefone.getId());
            instrutor.getTelefones().remove(telefone);
        }
    }

}
