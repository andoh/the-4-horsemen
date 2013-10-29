package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayDeque;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	String col[] = {"Date","Time","Sum"};
	DefaultTableModel model = new DefaultTableModel(col,4);
    JTable history;
    
	
    // TODO - implement!
		
   
	public HistoryTab() {} 
    
	public Component draw() {
        // TODO - Sales history tabel  
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		history=new JTable(model){@Override
			public boolean isCellEditable(int arg0, int arg1){
			return false;
		}
		};
		panel.add(history);
		GridBagConstraints gc = new GridBagConstraints();
	    GridBagLayout gb = new GridBagLayout();
	    gc.fill = GridBagConstraints.BOTH;
	    gc.weightx = 1.0;
	    gc.weighty = 1.0;
	    
	    JScrollPane scrollPane = new JScrollPane(history);
	    
	    panel.setLayout(gb);
	    panel.add(scrollPane, gc);
	    
		while(orders.isEmpty()==false){
			String[] temp = (String[]) orders.poll();
			history.setValueAt(temp[0], 0, 0);
			history.setValueAt(temp[1], 1, 0);
			history.setValueAt(temp[2], 2, 0);
				
		}
		
		
		
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