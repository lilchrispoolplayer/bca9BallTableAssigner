/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bcaleaguetableassigner;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.Border;

/**
 *
 * @author c_dra
 */
public class TableHeaderBorderRenderer extends DefaultTableCellRenderer {
    private final Border border;

    public TableHeaderBorderRenderer() {
        border = BorderFactory.createLineBorder(java.awt.Color.BLACK);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((DefaultTableCellRenderer) comp).setBorder(border);
        ((DefaultTableCellRenderer) comp).setHorizontalAlignment(JLabel.CENTER);
        return comp;
    }
}
