package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayDeque;
import java.util.ArrayList;


import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */


public class HistoryTab {
    
	
	DefaultTableModel model = new DefaultTableModel(4,4);
    JTable history = new JTable(model);
    ArrayDeque<Object> orders = new ArrayDeque<Object>();
    
	
    // TODO - implement!
		
   
	public HistoryTab() {} 
    
    public ArrayDeque<Object> getOrders() {
		return orders;
	}

	public void setOrders(ArrayDeque<Object> orders) {
		this.orders = orders;
	}

	public Component draw() {
        // TODO - Sales history tabel  
		JPanel panel = new JPanel();
		
		String[] sisend = (String[]) orders.pollFirst();
		
		

		history.setValueAt(sisend[0], arg1, arg2);
		history.setValueAt(sisend[1], arg1, arg2);
		history.setValueAt(sisend[2], arg1, arg2);
		
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