/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util.tablemodel;

import br.com.mario.model.Turma;
import br.com.mario.util.GenericTableModel;
import br.com.mario.util.Util;
import static br.com.mario.util.Util.convertToString;
import java.util.List;

/**
 *
 * @author mario
 */
public class TurmaTableModel extends GenericTableModel<Turma> {

    private String[] colunas = {"Codigo", "Nome", "Horário", "Duração", "Data Inicio", "Data Fim", "Editar", "Remover"};

    public TurmaTableModel(List<Turma> rows) {
        super(rows);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return rows.get(row).getId();
            case 1:
                return rows.get(row).getNome();
            case 2:
                return rows.get(row).getHorario();
            case 3:
                return rows.get(row).getDuracao();
            case 4:
                return convertToString(rows.get(row).getDataInicio());
            case 5:
                return convertToString(rows.get(row).getDataFim());
            case 6:
                return "Editar";
            case 7:
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
