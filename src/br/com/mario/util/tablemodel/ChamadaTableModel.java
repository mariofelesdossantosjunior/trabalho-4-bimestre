/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util.tablemodel;

import br.com.mario.model.Chamada;
import br.com.mario.util.GenericTableModel;
import br.com.mario.util.Util;
import java.util.List;

/**
 *
 * @author mario
 */
public class ChamadaTableModel extends GenericTableModel<Chamada> {

    private String[] colunas = {"Codigo", "Data", "Turma", "Visualizar"};

    public ChamadaTableModel(List<Chamada> rows) {
        super(rows);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return rows.get(row).getId();
            case 1:
                return Util.convertToString(rows.get(row).getData());
            case 2:
                return rows.get(row).getItemChamadas().stream().findFirst().get().getMatricula().getTurma().getNome();
            case 3:
                return "Visualizar";
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
