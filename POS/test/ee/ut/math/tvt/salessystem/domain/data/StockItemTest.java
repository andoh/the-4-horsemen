package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StockItemTest {
	
	private StockItem item1, item2;
	
	@Before
	public void setUp() {
		item1 = new StockItem((long) 101, "Testiasi", "Blabla", 13, 10);
		item2 = new StockItem(item1);
	}
	
	@Test
	public void testClone() {
		// We never actually clone, instead we have a special constructor 
		// StockItem(StockItem sti), so we will test just that
		assertEquals(item1.getId(), item2.getId());
		assertEquals(item1.getName(), item2.getName());
		assertEquals(item1.getPrice(), item2.getPrice(), 0.0001);
		assertEquals(item1.getQuantity(), item2.getQuantity());
		
	}
	
	@Test
	public void testGetColumn() {
		assertEquals(item1.getColumn(0),(long) 101);
		
	}
}
