package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {

	private long highestId = 0;
	
	public HistoryTableModel() {
		super(new String[] { "Id", "Date", "Time", "Order sum"});
	}
	
	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
			return item.getTime();
		case 3:
			return item.getSum();
		}
		return null;
	}
	
	
	public void addItem(final HistoryItem historyItem) {
//			historyItem.setId(highestId + 1);
//			highestId += 1;
//			
        
			try {
				rows.add(historyItem);
//				log.debug("Found existing item " + historyItem.getName()
//						+ " increased quantity by " + historyItem.getQuantity());
			}
			catch (NoSuchElementException e) {
				System.out.println(e);
//				log.debug("Added " + historyItem.getId()
//						+ " total sum of " + historyItem.getSum());
			}
			fireTableDataChanged();
    }

}
