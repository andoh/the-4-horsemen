package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.ArrayDeque;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */


public class HistoryTab {
	
	ArrayDeque<Object> orders = new ArrayDeque<Object>();
	
	public ArrayDeque<Object> getOrders() {
		return orders;
	}

	public void setOrders(ArrayDeque<Object> orders) {
		this.orders = orders;
	}

	DefaultTableModel model = new DefaultTableModel(4,4);
    JTable history = new JTable(model);
    
	
    // TODO - implement!
		
   
	public HistoryTab() {} 
    
	public Component draw() {
        // TODO - Sales history tabel  
		JPanel panel = new JPanel();
		
		while(!orders.isEmpty()){
			String[] temp = (String[]) orders.poll();
			history.setValueAt(temp[0], 1, 1);
			history.setValueAt(temp[1], 1, 1);
			history.setValueAt(temp[2], 1, 2);
				
		}
		
		panel.add(history);
		
		panel.setBackground(Color.WHITE);
		
		panel.updateUI();
		return panel;
		
      }
      
	private GridBagConstraints getConstraintsForGoodsPanel() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.BOTH;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 1.0;

    return gc;
	}
}