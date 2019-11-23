/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util.tablemodel;

import br.com.mario.model.Matricula;
import br.com.mario.util.GenericTableModel;
import static br.com.mario.util.Util.convertToString;
import java.util.List;

/**
 *
 * @author mario
 */
public class MatriculaTableModel extends GenericTableModel<Matricula> {

    private final String[] colunas = {"Data", "Aluno", "Turma", "Editar", "Remover"};

    public MatriculaTableModel(List<Matricula> rows) {
        super(rows);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return convertToString(rows.get(row).getDataMatricula());
            case 1:
                return rows.get(row).getAluno().getNome();
            case 2:
                return rows.get(row).getTurma().getNome();
            case 3:
                return "Editar";
            case 4:
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
