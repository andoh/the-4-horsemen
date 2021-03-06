package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

public class StockTab {
  private static Connection getHSQLConnection() throws Exception {
	    Class.forName("org.hsqldb.jdbcDriver");
	    System.out.println("Driver Loaded.");
	    String url = "jdbc:hsqldb:hsql://localhost/POS";
	    return DriverManager.getConnection(url, "sa", "");
  }

  private JButton addItem;

  private JFrame newProductWindow;
  
  private JTextField name = new JTextField(10);
  private JTextField desc = new JTextField(10);
  private JTextField quantity = new JTextField(5);
  private JTextField price = new JTextField(5);
  private JLabel wrongValues = new JLabel("Wrong values");
    
  private SalesSystemModel model;

  private StockTableModel tableModel;
  
  private JTable warehouse;
  
  private PurchaseItemPanel ref;
  
  public StockTab(SalesSystemModel model) {
    this.model = model;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
	 
	tableModel = model.getWarehouseTableModel();
	  
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
//    warehouse = new JTable (tableModel) {
//    	@Override
//    	public boolean isCellEditable(int arg0, int arg1) {
//    		return false;
//    	}
//    };
//    panel.add(warehouse);

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
        
    GridBagConstraints gbc = new GridBagConstraints();
        
        ArrayDeque<Component> el = new ArrayDeque<Component>();
        
        el.add(new JLabel("Name"));
        el.add(name);
        name.setColumns(10);
        el.add(new JLabel("Description"));
        el.add(desc);
        desc.setColumns(10);
        el.add(new JLabel("Price"));
        el.add(price);
        price.setColumns(5);
        el.add(new JLabel("Quantity"));
        el.add(quantity);
        quantity.setColumns(5);
        wrongValues.setVisible(false);
        
        JButton accept = new JButton("Accept");
        JButton cancel = new JButton("Cancel");
        
        
        el.add(accept);
        el.add(cancel);
        
        
        cancel.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                        newProductWindow.dispose();
                }
        });
        
        accept.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        boolean toContinue = true;
                        
                        String currName = name.getText();
                        String currDesc = desc.getText();
                        String currPriceStr = price.getText();
                        String currQuantityStr = quantity.getText();
                        
                        double currPrice=0;
                        int currQuantity=0;
                        
                        if(currName.isEmpty() || currDesc.isEmpty()) {
                                wrongValues.setVisible(true);
                                newProductWindow.pack();
                                toContinue = false;
                        }
                        
                        try{
                                currPrice = Double.parseDouble(currPriceStr);
                                if(currPrice < 0) {
                                        throw(new Exception());
                                }
                        } catch(Exception ex) {
                                price.setText("");
                                wrongValues.setVisible(true);
                                newProductWindow.pack();
                                toContinue = false;
                        }
                        
                        try{
                                currQuantity = Integer.parseInt(currQuantityStr);
                                if(currQuantity < 0) {
                                        throw(new Exception());
                                }
                        } catch(Exception ex) {
                                quantity.setText("");
                                wrongValues.setVisible(true);
                                newProductWindow.pack();
                                toContinue = false;
                        }
                        
                        // if everything is correct
                        // add product to list
                        if(toContinue) {
                                long lastId = (long) model.getWarehouseTableModel().getRowCount();
                                long andmebaasiId = lastId+1;
                                Connection conn;
                                
                                try {
                                	conn = getHSQLConnection();
                                	System.out.println("Yhendatud");
                                	Statement st = conn.createStatement();
                                	
                                	st.executeUpdate("INSERT INTO StockItem(id,name,price,quantity,description) values "
                                			+ "("+andmebaasiId+",'"+name.getText()+"','"+Double.parseDouble(price.getText())+"','"
                                			+ Integer.parseInt(quantity.getText())+"','"+desc.getText()+"')");
                                	st.close();
                                	conn.close();                                
                                } catch (Exception e1) {
                                	System.out.println("StockTab - lisamine ei 6nnestunud");
                                }
                                
                                StockItem stockItem = new StockItem(lastId+1,name.getText(),desc.getText(),Double.parseDouble(price.getText()),Integer.parseInt(quantity.getText()));
                                model.getWarehouseTableModel().addItem(stockItem);
                                newProductWindow.dispose();
                        } 
                        //ref = new PurchaseItemPanel(model);
                        //ref.refreshItems(); // List gets updated, but new items are not shown
                }
                
                
        });
        
        for (int i = 0; i < 5 ; i++ ){
                gbc.gridy = i;
                
                for (int j = 0; j < 2 ; j++){
                        gbc.gridx = j;
                        
                        newProductWindow.add( el.poll(),gbc);
                        
                }
        }
        gbc.gridy = 5;
        gbc.gridx = 0;
        newProductWindow.add(wrongValues, gbc);
    

    addItem = new JButton("Add");
    addItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                    newProductWindow.setVisible(true);
                    newProductWindow.pack();
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