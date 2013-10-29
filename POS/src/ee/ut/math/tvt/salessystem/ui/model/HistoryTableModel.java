package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

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
	
	
	public void addItem(final HistoryItem item) {
		item.setId(highestId + 1);
		highestId += 1;
		
        rows.add(item);
	  
        fireTableDataChanged();
    }

}
