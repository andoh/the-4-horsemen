package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;


public class StockTableModelTest {
	
	SalesDomainControllerImpl sdc = new SalesDomainControllerImpl();
	
	SalesSystemModel ssm = new SalesSystemModel(sdc);

	PurchaseItemPanel pip = new PurchaseItemPanel(ssm);
	
	SalesSystemTableModel<StockItem> sstm;
	
	private StockItem item1;
		
	@Before
	public void setUp() {
		item1 = new StockItem((long) 103, "item1", "asi1", 15, 5);
	}
	// Due to our current design testValidateNameUniqueness() cannot be tested separately. For now 
	// we will use an item that we know exists
	@Test
	public void testValidateNameUniqueness() {
		assertEquals(pip.validateNameUniqueness("Free Beer"), false);
	}
	
	@Test
	public void testHasEnoughInStock() {
		ssm.getWarehouseTableModel().addItem(item1);
		ssm.getCurrentPurchaseTableModel().addItem(new SoldItem(item1,6));
		assertEquals(pip.HasEnoughInStock(item1), false);
	}
	
	@Test
	public void GetItemByIdWhenItemExists() {
		ssm.getWarehouseTableModel().addItem(item1);
		assertEquals(pip.GetItemById((long) 103), item1);
	}
	
	@Test
	public void testGetItemByIdWhenThrowsException () {
		assertEquals(pip.GetItemById((long) 100), false);
	}

}
