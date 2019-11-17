/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.dao;

import java.util.List;

/**
 *
 * @author aluno
 */
public interface Dao<PK, T>{
    
    public boolean create(T entity);
    public T retrieve(PK pk);
    public boolean update(T entity);
    public void delete(PK pk);
    public List<T> findAll();
}
