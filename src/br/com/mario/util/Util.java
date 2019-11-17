/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author mario
 */
public class Util {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static java.sql.Date convertToDate(String value) {
        try {
            return new java.sql.Date(dateFormat.parse(value).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String convertToString(Date date) {
        return dateFormat.format(date);
    }
}
