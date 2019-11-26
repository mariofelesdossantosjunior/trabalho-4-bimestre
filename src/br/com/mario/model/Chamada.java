/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author mario
 */
public class Chamada {

    private int id;
    private Date data;
    List<ItemChamada> itemChamadas;

    public Chamada() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemChamada> getItemChamadas() {
        return itemChamadas;
    }

    public void setItemChamadas(List<ItemChamada> itemChamadas) {
        this.itemChamadas = itemChamadas;
    }

    @Override
    public String toString() {
        return "Chamada{" + "id=" + id + ", data=" + data + '}';
    }

}
