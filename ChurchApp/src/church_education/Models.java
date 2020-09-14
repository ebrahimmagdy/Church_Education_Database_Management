/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package church_education;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ebrahim
 */
public class Models {
    private DefaultTableModel model = new DefaultTableModel();

    public void addRow(Object[] row) {
        model.addRow(row);
    }
    
    public void setModel(DefaultTableModel model) {
        this.model = model;
    }
    
    public DefaultTableModel getModel() {
        return model;
    }
    
}
