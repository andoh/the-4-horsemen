package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.HistoryViewModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */


public class HistoryTab {
		
	private SalesSystemModel model;
	
	private final SalesDomainController domainController;
	
	private HistoryTableModel tableModel;
    
	private HistoryViewModel viewModel;
	
	private JTable history;
	
	private JTable view;
	
	private JFrame historyView;
	
	private JPanel historyViewPanel;
	
	public HistoryTab(SalesDomainController controller,
		      SalesSystemModel modelIn) {
		
		this.domainController = controller;
	    this.model = modelIn;
	} 	
    // TODO - implement!
		

	public Component draw() {
		
		tableModel = model.getCurrentHistoryTableModel();
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
				
				historyView = new JFrame();
				historyView.setTitle("Purchase contents");
				historyView.setSize(500, 300);
				historyView.setLocation(250, 200);
				historyViewPanel=new JPanel();
				GridBagConstraints gc = new GridBagConstraints();
				GridBagLayout gb = new GridBagLayout();
				gc.fill = GridBagConstraints.BOTH;
				gc.weightx = 1.0;
				gc.weighty = 1.0;
				JScrollPane scrollpane= new JScrollPane();
				Long p = Long.parseLong(String.valueOf(history.rowAtPoint(e.getPoint())));	
				//model.getCurrentHistoryViewModel().populateWithData(domainController.loadHistoryView());
				
//				JTable table = new JTable(model.getCurrentHistoryViewModel());
				historyView.add(historyViewPanel);
				historyViewPanel.setLayout(gb);
				historyViewPanel.add(scrollpane,gc);
				historyViewPanel.setVisible(true);
				
				viewModel = model.getCurrentHistoryViewModel();
				try {
					viewModel.populateWithData(domainController.loadHistoryView());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				historyView.setLayout(new FlowLayout());		
				
				view=new JTable(viewModel){@Override
					public boolean isCellEditable(int arg0, int arg1){
					return false;
					}
				};
				
				historyViewPanel.add(view);
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
		
		
		
		
//		historyView.setLayout(new GridBagLayout());
		

		
		
		
		panel.add(history);
		GridBagConstraints gc = new GridBagConstraints();
	    GridBagLayout gb = new GridBagLayout();
	    gc.fill = GridBagConstraints.BOTH;
	    gc.weightx = 1.0;
	    gc.weighty = 1.0;
	    
	    JScrollPane scrollPane = new JScrollPane(history);
	    
	    panel.setLayout(gb);
	    panel.add(scrollPane, gc);
	    
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