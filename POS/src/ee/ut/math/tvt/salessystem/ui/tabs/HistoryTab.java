package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */


public class HistoryTab {
		
	private SalesSystemModel model;
	
	private final SalesDomainController domainController;
	
	private HistoryTableModel historyModel;
	
	private JFrame historyView;
	
	private int k = 0;
	
	ArrayDeque<Object> orders = new ArrayDeque<Object>();
	
	public HistoryTab(SalesDomainController controller,
		      SalesSystemModel modelIn) {
		
		this.domainController = controller;
	    this.model = modelIn;
	} 	

	public ArrayDeque<Object> getOrders() {
		return orders;
	}

	public void setOrders(ArrayDeque<Object> orders) {
		this.orders = orders;
	}
	String col[] = {"Date","Time","Sum"};
	HistoryTableModel tableModel;
    JTable history;
    
	
    // TODO - implement!
		

	public Component draw() {
		historyModel = model.getCurrentHistoryTableModel();
		tableModel = model.getCurrentHistoryTableModel();
		//tableModel = new DefaultTableModel(col,4)
		
		
		
        // TODO - Sales history tabel  
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());		
		
		history=new JTable(tableModel){@Override
			public boolean isCellEditable(int arg0, int arg1){
			return false;
			}
		};
		
		history.addMouseListener(new MouseListener()  
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				
				historyView.setVisible(true);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		if(historyView != null)
			historyView.dispose();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		historyView = new JFrame();
		historyView.setTitle("Purchase contents");
		historyView.setSize(500, 300);
		historyView.setLocation(250, 200);
		historyView.setLayout(new GridBagLayout());
		
		JTable table = new JTable(model.getCurrentHistoryViewModel());
		JScrollPane scrollPanel = new JScrollPane(table);
		historyView.add(scrollPanel, gbc);
		
		
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
			ArrayDeque<Object> temp = getOrders();
			Object[] temps = (Object[]) temp.pop(); // TODO: cast annab errori
			System.out.println(temps[0]);
			history.setValueAt(temps[0], 0, 0);
			history.setValueAt(temps[1], 1, 0);
			history.setValueAt(temps[2], 2, 0);
				
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