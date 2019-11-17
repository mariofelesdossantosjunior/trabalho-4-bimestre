/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aluno
 */
public abstract class GenericTableModel<E> extends AbstractTableModel {

    protected List<E> rows;

    public GenericTableModel(List<E> rows) {
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return qtdeColunas();
    }

    public abstract String getNomeColuna(int coluna);

    public abstract int qtdeColunas();

    @Override
    public String getColumnName(int column) {
        return getNomeColuna(column);
    }

    public E getValueAtRow(int row) {
        return rows.get(row);
    }

    public void setValueAtRow(int row, E object) {
        rows.set(row, object);
    }

    public void setListData(List<E> data) {
        this.rows = data;
        this.fireTableDataChanged();
    }

    public List<E> getRows() {
        return rows;
    }

}
