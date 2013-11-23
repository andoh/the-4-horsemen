package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	
	private StockItem item1, item2;
	
	@Before
	public void setUp() {
		item1 = new StockItem((long) 101, "Testiasi", "Blabla", 13, 10);
		item2 = new StockItem(item1);
	}
	
	@Test
	public void testClone() {
		// Me tegelt ei kloonigi mitte kunagi. Selle asemel on meil StockItemis konstruktor
		// StockItem(StockItem sti), ehk siis testida tuleks just seda
		assertEquals(item1, item2);
		
	}
	
	public void testGetColumn() {
		
	}
	

}
