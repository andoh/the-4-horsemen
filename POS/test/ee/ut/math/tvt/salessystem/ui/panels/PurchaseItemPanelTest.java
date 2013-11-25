package ee.ut.math.tvt.salessystem.ui.panels;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class PurchaseItemPanelTest {

	SalesDomainControllerImpl sdc = new SalesDomainControllerImpl();
	
	SalesSystemModel ssm = new SalesSystemModel(sdc);

	PurchaseItemPanel pip = new PurchaseItemPanel(ssm);
	
	private StockItem item1;
	
	private Double summa = 0.0;
	
	@Before
	public void setUp () {
		item1 = new StockItem((long) 102, "PIPT", "PIPI", 10, 4);
	}
	
	@Test
	public void testAddSoldItem() {
		ssm.getCurrentPurchaseTableModel().addItem(new SoldItem(item1,3));
		assertEquals(ssm.getCurrentPurchaseTableModel().getItemById(102).getStockItem(), item1);
	}
	
	@Test
	public void testGetSumWithNoItems() {
		try {
			summa = (Double)(ssm.getCurrentPurchaseTableModel().getValueAt(0, ssm.getCurrentPurchaseTableModel().findColumn("Sum")));
		} catch (Exception e1) {
			summa = null;
		}
		assertEquals(summa, null);
	}

	@Test
	public void testGetSumWithOneItem() {
		ssm.getCurrentPurchaseTableModel().addItem(new SoldItem(item1,1));
		summa = (Double)(ssm.getCurrentPurchaseTableModel().getValueAt(0, ssm.getCurrentPurchaseTableModel().findColumn("Sum")));
		assertEquals(summa, 10, 0.0001);
	}

	@Test
	public void testGetSumWithMultipleItems() {
		ssm.getCurrentPurchaseTableModel().addItem(new SoldItem(item1,2));
		summa = (Double)(ssm.getCurrentPurchaseTableModel().getValueAt(0, ssm.getCurrentPurchaseTableModel().findColumn("Sum")));
		assertEquals(summa, 20, 0.0001);
		
	}

}
