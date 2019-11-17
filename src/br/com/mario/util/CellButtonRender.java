/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.util;

import java.awt.Component;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mario
 */
public class CellButtonRender extends JButton implements TableCellRenderer {

    public CellButtonRender(String title) {
        setOpaque(true);
    }

    public CellButtonRender(String title, String pathIcon) {
        setOpaque(true);
        setIcon(new javax.swing.ImageIcon(new File(pathIcon).getAbsolutePath()));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj,
            boolean selected, boolean focused, int row, int col) {
        if (obj != null) {
            setText(obj.toString());
            return this;
        } else {
            return null;
        }
    }
}
