/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util.tablemodel;

import br.com.mario.model.Instrutor;
import br.com.mario.util.GenericTableModel;
import static br.com.mario.util.Util.convertToString;
import java.util.List;

/**
 *
 * @author mario
 */
public class InstrutorTableModel extends GenericTableModel<Instrutor> {

    private String[] colunas = {"Codigo", "RG", "Nome", "Nascimento", "Titulação", "Editar", "Remover"};

    public InstrutorTableModel(List<Instrutor> rows) {
        super(rows);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return rows.get(row).getId();
            case 1:
                return rows.get(row).getRg();
            case 2:
                return rows.get(row).getNome();
            case 3:
                return convertToString(rows.get(row).getNascimento());
            case 4:
                return rows.get(row).getTitulacao();
            case 5:
                return "Editar";
            case 6:
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
