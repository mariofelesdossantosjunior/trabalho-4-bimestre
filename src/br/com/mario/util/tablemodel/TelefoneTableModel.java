/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util.tablemodel;

import br.com.mario.model.Telefone;
import br.com.mario.util.GenericTableModel;
import java.util.List;

/**
 *
 * @author mario
 */
public class TelefoneTableModel extends GenericTableModel<Telefone> {

    private String[] colunas = {"Codigo", "Tipo", "Número", "Remover"};

    public TelefoneTableModel(List<Telefone> rows) {
        super(rows);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return rows.get(row).getId();
            case 1:
                return rows.get(row).getTipo();
            case 2:
                return rows.get(row).getNumero();
            case 3:
                return "Remover";

        }
        return null;
    }

    @Override
    public String getNomeColuna(int coluna) {
        return colunas[coluna];
    }

    @Override
    public int qtdeColunas() {
        return colunas.length;
    }

}
