package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SoldItemTest {
	
	private StockItem st_item;
	
	private SoldItem so_item1, so_item2;
	
	@Before
	public void setUp() {
		st_item = new StockItem ((long) 101, "Testing", "Blaa", 35, 4);
		so_item1 = new SoldItem (st_item, 2);
		so_item2 = new SoldItem (st_item, 0);
		
	}
	
	@Test
	public void testGetSum() {
		assertEquals(70, so_item1.getSum(), 0.0001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		assertEquals(0, so_item2.getSum(), 0.0001);
		
	}

}
