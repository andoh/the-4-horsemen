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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab {

  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  private final SalesDomainController domainController;

  private JButton newPurchase;

  private JButton submitPurchase;

  private JButton cancelPurchase;

  private PurchaseItemPanel purchasePane;

  private SalesSystemModel model;
  
  private JLabel change = new JLabel();
  
  private JTextField payment = new JTextField(4);
  
  private JLabel sum = new JLabel();
  
  private JFrame uus;
 
  public PurchaseTab(SalesDomainController controller,
      SalesSystemModel model)
  {
    this.domainController = controller;
    this.model = model;
  }


  /**
   * The purchase tab. Consists of the purchase menu, current purchase dialog and
   * shopping cart table.
   */
  public Component draw() {
    JPanel panel = new JPanel();

    // Layout
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new GridBagLayout());

    // Add the purchase menu
    panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

    // Add the main purchase-panel
    purchasePane = new PurchaseItemPanel(model);
    panel.add(purchasePane, getConstraintsForPurchasePanel());

    return panel;
  }




  // The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
  private Component getPurchaseMenuPane() {
    JPanel panel = new JPanel();

    // Initialize layout
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gc = getConstraintsForMenuButtons();

    // Initialize the buttons
    newPurchase = createNewPurchaseButton();
    submitPurchase = createConfirmButton();
    cancelPurchase = createCancelButton();

    // Add the buttons to the panel, using GridBagConstraints we defined above
    panel.add(newPurchase, gc);
    panel.add(submitPurchase, gc);
    panel.add(cancelPurchase, gc);

    return panel;
  }


  // Creates the button "New purchase"
  private JButton createNewPurchaseButton() {
    JButton b = new JButton("New purchase");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        newPurchaseButtonClicked();
      }
    });

    return b;
  }

  // Creates the "Confirm" button
  private JButton createConfirmButton() {
    JButton b = new JButton("Confirm");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }


  // Creates the "Cancel" button
  private JButton createCancelButton() {
    JButton b = new JButton("Cancel");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }





  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


  /** Event handler for the <code>new purchase</code> event. */
  protected void newPurchaseButtonClicked() {
    log.info("New sale process started");
    try {
      domainController.startNewPurchase();
      startNewSale();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /**  Event handler for the <code>cancel purchase</code> event. */
  protected void cancelPurchaseButtonClicked() {
    log.info("Sale cancelled");
    try {
      domainController.cancelCurrentPurchase();
      endSale();
      model.getCurrentPurchaseTableModel().clear();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /** Event handler for the <code>submit purchase</code> event. */
  protected void submitPurchaseButtonClicked() {
    log.info("Sale complete");
    try {
    	if(uus != null) {
    		uus.dispose();
    	}
    	uus = new JFrame();
    	int len = model.getCurrentPurchaseTableModel().getRowCount();
    	
    	double summa = 0.0;
    	for (int i = 0 ; i<model.getCurrentPurchaseTableModel().getRowCount() ; i++){
    		summa = summa + (Double)(model.getCurrentPurchaseTableModel().getValueAt(i, model.getCurrentPurchaseTableModel().findColumn("Sum")));
    	}
    	
    	
    	
    	uus.setTitle("Confirm order");
    	uus.setSize(200, 400);
    	uus.setLocation(100, 100);
    	uus.setLayout(new GridBagLayout());
    	
    	GridBagConstraints gc = new GridBagConstraints();
    	
    	ArrayDeque<Component> elements = new ArrayDeque<Component>();
    	
    	elements.add(new JLabel("Total"));
    	elements.add(sum);
    	elements.add(new JLabel("Payment"));
    	elements.add(payment);
    	elements.add(new JLabel("Change"));
    	elements.add(change);
    	
    	JButton accept = new JButton("Accept");
    	JButton cancel = new JButton("Cancel");
    	
    	elements.add(accept);
    	elements.add(cancel);
    	
    	for (int i = 0; i < 4 ; i++ ){
    		gc.gridy = i;
    		
    		for (int j = 0; j < 2 ; j++){
    			gc.gridx = j;
    			
    			uus.add( elements.poll(),gc);
    			
    		}
    	}
    	
   	
 
    	
    	sum.setText(String.valueOf(summa));
    	change.setText("0.0");
    	
    	
    	uus.setVisible(true);
    	
    	accept.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
					domainController.submitCurrentPurchase(
					          model.getCurrentPurchaseTableModel().getTableRows()
							);
				} catch (VerificationFailedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		      endSale();
    		}
    	});
    	
    	cancel.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			uus.setVisible(false);
    		}
    	});
    	payment.getDocument().addDocumentListener(new DocumentListener(){
    		public void changedUpdate(DocumentEvent e) {
    			try {
    				  				
    				if (Double.parseDouble(payment.getText())<=0.0){
    					payment.setText("0.0");
    					change.setText("");
    				}
    				else{
    					change.setText(String.valueOf(
    	    					Double.parseDouble(payment.getText())-
    	            			Double.parseDouble(sum.getText())));
    				}
    					
    			}
    			catch (Exception ex){
    				change.setText("");
    			}
    		}
    		public void removeUpdate(DocumentEvent e) {
    			changedUpdate(e);
    		}
    		public void insertUpdate(DocumentEvent e) {
    			changedUpdate(e);
    		}
    	});
    	/*
    	payment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	change.setText(String.valueOf(
            			Double.parseDouble(payment.getText())-
            			Double.parseDouble(sum.getText())));
            }
        });
        */
    	
      log.debug("Contents of the current basket:\n" + model.getCurrentPurchaseTableModel());
      domainController.submitCurrentPurchase(
          model.getCurrentPurchaseTableModel().getTableRows()
      );
      endSale();
      model.getCurrentPurchaseTableModel().clear();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /* === Helper methods that bring the whole purchase-tab to a certain state
   *     when called.
   */

  // switch UI to the state that allows to proceed with the purchase
  private void startNewSale() {
    //purchasePane.reset();

    purchasePane.setEnabled(true);
    submitPurchase.setEnabled(true);
    cancelPurchase.setEnabled(true);
    newPurchase.setEnabled(false);
  }

  // switch UI to the state that allows to initiate new purchase
  private void endSale() {
    //purchasePane.reset();

    cancelPurchase.setEnabled(false);
    submitPurchase.setEnabled(false);
    newPurchase.setEnabled(true);
    purchasePane.setEnabled(false);
  }




  /* === Next methods just create the layout constraints objects that control the
   *     the layout of different elements in the purchase tab. These definitions are
   *     brought out here to separate contents from layout, and keep the methods
   *     that actually create the components shorter and cleaner.
   */

  
  
  private GridBagConstraints getConstraintsForPurchaseMenu() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    return gc;
  }


  private GridBagConstraints getConstraintsForPurchasePanel() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.BOTH;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 1.0;

    return gc;
  }


  // The constraints that control the layout of the buttons in the purchase menu
  private GridBagConstraints getConstraintsForMenuButtons() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.weightx = 0;
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridwidth = GridBagConstraints.RELATIVE;

    return gc;
  }

}
