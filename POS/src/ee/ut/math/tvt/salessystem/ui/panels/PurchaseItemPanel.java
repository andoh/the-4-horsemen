package ee.ut.math.tvt.salessystem.ui.panels;

import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // Text field on the dialogPane
    private JTextField barCodeField;
    //private JTextField barCodeField;
    private JTextField quantityField;
    private JComboBox nameField;
    private JTextField priceField;
    private ArrayList<String> names;
    private List<StockItem> products;
    private JButton addItemButton;

    // Warehouse model
    protected SalesSystemModel model;
    
    public SalesSystemModel getModel() {
		return model;
	}

	public void setModel(SalesSystemModel model) {
		this.model = model;
	}

	SalesDomainControllerImpl sdc = new SalesDomainControllerImpl();

    /**
     * Constructs new purchase item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
    public PurchaseItemPanel(SalesSystemModel model) {
    	this.model = model;
    	
        setLayout(new GridBagLayout());

        add(drawDialogPane(), getDialogPaneConstraints());
        add(drawBasketPane(), getBasketPaneConstraints());

        setEnabled(false);
    }

    // shopping cart pane
    private JComponent drawBasketPane() {

        // Create the basketPane
        JPanel basketPane = new JPanel();
        basketPane.setLayout(new GridBagLayout());
        basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

        // Create the table, put it inside a scollPane,
        // and add the scrollPane to the basketPanel.
        JTable table = new JTable(model.getCurrentPurchaseTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        basketPane.add(scrollPane, getBacketScrollPaneConstraints());

        return basketPane;
    }
    
    public void refreshItems () {
        products = sdc.loadWarehouseState();
        names = new ArrayList<String>();
    	
        //Gets product list from loadWarehouseState
        for (StockItem product : products) {       		
        	names.add(product.getName());
        }       
    }

    // purchase dialog
    private JComponent drawDialogPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Product"));
        
        refreshItems();
                
        // Initialize the textfields(when start)
        barCodeField = new JTextField(String.valueOf(products.get(0).getId()));
        quantityField = new JTextField("1");
        nameField = new JComboBox(names.toArray());
        priceField = new JTextField(String.valueOf(products.get(0).getPrice()));
        
        //Fill dialog when Action performed.
        nameField.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		fillDialogFields();
        	}
        });
        
        quantityField.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		fillDialogFields();
        	}
        });
                
        // Fill the dialog fields if the bar code text fields loses focus
        /*
        nameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }
            
            public void focusLost(FocusEvent e) {     
            	fillDialogFields();
            }
        });
		*/
        quantityField.setEditable(true);
        nameField.setEditable(false);
        barCodeField.setEditable(false);
        priceField.setEditable(false);

        // == Add components to the panel

        // - bar code
        panel.add(new JLabel("Bar code:"));
        panel.add(barCodeField);

        // - amount
        panel.add(new JLabel("Amount:"));
        panel.add(quantityField);

        // - name
        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        // - price
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        // Create and add the button
        addItemButton = new JButton("Add to cart");
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemEventHandler(getStockItemByBarcode(Long.parseLong(barCodeField.getText())));
            }
        });

        panel.add(addItemButton);

        return panel;
    }

    // Fill dialog with data from the "database".
    public void fillDialogFields() {
        
    	StockItem stockItem = (products.get(names.indexOf(nameField.getSelectedItem())));
    	
    	if (stockItem != null) {
        	
        	String barId = String.valueOf(stockItem.getId());
        	
        	barCodeField.setText(barId);
        	
        	String priceString = String.valueOf(stockItem.getPrice()*Long.parseLong(quantityField.getText()));
        	
        	priceField.setText(priceString);
       	}
    	 else {
        	reset();
        } 
           	
   
    }

    
    // Search the warehouse for a StockItem with the bar code entered
    // to the barCode textfield.
    protected StockItem getStockItemByBarcode(Long id) {
        try {
            return model.getWarehouseTableModel().getItemById(id);
        } catch (NumberFormatException ex) {
           return null;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }
    
    // Will be mo
	public boolean validateNameUniqueness(String Name) {
		
		List<StockItem> tempList = sdc.loadWarehouseState();
				
		for (StockItem item : tempList) {
	        if (Name.equals(item.getName())) {
	            return false;
	        }
	    }
	    return true;
	}
    
	public Object GetItemById(long id) {
		try {
			return model.getWarehouseTableModel().getItemById(id);
		} catch (Exception e1) {
			return false;
		}
	}

    public boolean HasEnoughInStock(StockItem sti) {
    	int quantity = 1;
        if (sti != null) {
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                quantity = 1;
            }
        }

        SoldItem si = model.getCurrentPurchaseTableModel().getItemById(sti.getId());
        int totalQuantity = quantity+si.getQuantity();
        if (totalQuantity>model.getWarehouseTableModel().getItemById(sti.getId()).getQuantity())
        	return false;
        return true;
    }

    /**
     * Add new item to the cart.
     */
    public void addItemEventHandler(StockItem stockItem) {
        // add chosen item to the shopping cart.
        int quantity = 1;
        if (stockItem != null) {
            
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                quantity = 1;
            }
            
        }
        
        try {
        	//Tries do find item in current table
        	SoldItem si = model.getCurrentPurchaseTableModel().getItemById(stockItem.getId());
        	//If not found, get value and add quantity
        	int totalQuantity = quantity+si.getQuantity();
        	
        	if (totalQuantity>model.getWarehouseTableModel().getItemById(stockItem.getId()).getQuantity()){
        		JOptionPane.showMessageDialog(null, "Insufficient goods in stock", "Input error",JOptionPane.ERROR_MESSAGE);;
        	}
        	else{
        		//Update quantity and sum
        		si.setQuantity(totalQuantity);
        		si.setSum(si.getQuantity()*si.getPrice());
        	}
        	
        	model.getCurrentPurchaseTableModel().fireTableDataChanged();
        	
        	
        	
        }catch (Exception e) {
        	if (quantity>(model.getWarehouseTableModel().getItemById(stockItem.getId())).getQuantity()){
        		JOptionPane.showMessageDialog(null, "Insufficient goods in stock", "Input error",JOptionPane.ERROR_MESSAGE);;
        	}
        	else{
        		model.getCurrentPurchaseTableModel()
                .addItem(new SoldItem(stockItem, quantity));
            	
        	}
        	}
    	}
    
    
    /**
     * Sets whether or not this component is enabled.
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.addItemButton.setEnabled(enabled);
        this.barCodeField.setEnabled(enabled);
        this.quantityField.setEnabled(enabled);
    }

    /**
     * Reset dialog fields.
     */
    public void reset() {
        barCodeField.setText("");
        quantityField.setText("1");
        nameField.setToolTipText("");
        priceField.setText("");
    }

    /*
     * === Ideally, UI's layout and behavior should be kept as separated as
     * possible. If you work on the behavior of the application, you don't want
     * the layout details to get on your way all the time, and vice versa. This
     * separation leads to cleaner, more readable and better maintainable code.
     * 
     * In a Swing application, the layout is also defined as Java code and this
     * separation is more difficult to make. One thing that can still be done is
     * moving the layout-defining code out into separate methods, leaving the
     * more important methods unburdened of the messy layout code. This is done
     * in the following methods.
     */

    // Formatting constraints for the dialogPane
    private GridBagConstraints getDialogPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 0d;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.NONE;

        return gc;
    }

    // Formatting constraints for the basketPane
    private GridBagConstraints getBasketPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 1.0;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;

        return gc;
    }

    private GridBagConstraints getBacketScrollPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        return gc;
    }

}
