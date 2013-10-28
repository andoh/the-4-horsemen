package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class StockTab {

  private JButton addItem;

  private JFrame newProductWindow;
  
  private JTextField name = new JTextField(10);
  private JTextField quantity = new JTextField(5);
  private JTextField price = new JTextField(5);
    
  private SalesSystemModel model;

  public StockTab(SalesSystemModel model) {
    this.model = model;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    panel.add(drawStockMenuPane(), gc);

    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);

    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;
    
    if(newProductWindow != null) {
    	newProductWindow.dispose();
    }
    newProductWindow = new JFrame();
    
    newProductWindow.setTitle("Insert new product");
    newProductWindow.setSize(200, 400);
    newProductWindow.setLocation(100, 100);
    newProductWindow.setLayout(new GridBagLayout());
    
    
    JTextField ProductName = new JTextField();
    JTextField ProductPrice = new JTextField();
    JTextField ProductQuantity = new JTextField();
    
    JLabel ProductNameL = new JLabel("Name");
    JLabel ProductPriceL = new JLabel("Price");
    JLabel ProductQuantityL = new JLabel("Quantity");
    
    GridBagConstraints gbc = new GridBagConstraints();
	
	ArrayDeque<Component> el = new ArrayDeque<Component>();
	
	el.add(new JLabel("Name"));
	el.add(name);
	el.add(new JLabel("Price"));
	el.add(price);
	el.add(new JLabel("Quantity"));
	el.add(quantity);
	
	JButton accept = new JButton("Accept");
	JButton cancel = new JButton("Cancel");
	
	el.add(accept);
	el.add(cancel);
	
	for (int i = 0; i < 4 ; i++ ){
		gbc.gridy = i;
		
		for (int j = 0; j < 2 ; j++){
			gbc.gridx = j;
			
			newProductWindow.add( el.poll(),gbc);
			
		}
	}
    

    addItem = new JButton("Add");
    addItem.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
    		newProductWindow.setVisible(true);
    	}
    	
    	
    });
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    panel.add(addItem, gc);

    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }


  // table of the wareshouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());

    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;

    panel.setLayout(gb);
    panel.add(scrollPane, gc);

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }

}
