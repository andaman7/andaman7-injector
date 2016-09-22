package com.andaman7.injector.views.tablemodels;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JTable;

/**
 *
 * @author Pierre-Yves
 */
public class TableRowSelectionModel extends DefaultListSelectionModel {
    
    private JTable parent;

    public TableRowSelectionModel(JTable parent) {
        this.parent = parent;
    }

    public JTable getParent() {
        return parent;
    }

    public void setParent(JTable parent) {
        this.parent = parent;
    }
}
