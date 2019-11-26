/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util.tablemodel;

import br.com.mario.model.Matricula;
import br.com.mario.util.GenericTableModel;
import java.util.List;

/**
 *
 * @author mario
 */
public class ChamadaMatriculaTableModel extends GenericTableModel<Matricula> {

    private final String[] colunas = {"Status", "Aluno"};
    private final Class[] columnClass = new Class[]{Boolean.class, String.class};

    public ChamadaMatriculaTableModel(List<Matricula> rows) {
        super(rows);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return rows.get(row).getStatus();
            case 1:
                return rows.get(row).getAluno().getNome();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Matricula row = rows.get(rowIndex);

        if (0 == columnIndex) {
            row.setStatus((Boolean) aValue);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return coluna == 0;
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
