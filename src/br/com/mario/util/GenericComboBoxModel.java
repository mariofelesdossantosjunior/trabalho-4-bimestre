/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author aluno
 */
public class GenericComboBoxModel<T> extends AbstractListModel<T>
        implements ComboBoxModel<T> {

    private List<T> data;
    private T itemSelect;

    public GenericComboBoxModel() {
        this.data = new ArrayList<>();
    }

    public GenericComboBoxModel(List<T> listaDados) {
        this.data = listaDados;
        if (this.data.size() > 0) {
            itemSelect = data.get(0);
        }
    }

    @Override
    public int getSize() {
        return this.data.size();
    }

    @Override
    public T getElementAt(int index) {
        return this.data.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.itemSelect = (T) anItem;
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        
    }

    @Override
    public T getSelectedItem() {
        return this.itemSelect;
    }

    public void addElement(T element) {
        data.add(element);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
    }

    public void addListElements(List<T> elements) {
        int primeiraLinha = getSize();
        data.addAll(elements);
        fireIntervalAdded(this, primeiraLinha, data.size());
        itemSelect = null;
    }

    public void clear() {
        this.data.clear();
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
