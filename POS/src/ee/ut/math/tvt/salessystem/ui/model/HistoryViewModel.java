package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.ViewItem;

public class HistoryViewModel extends SalesSystemTableModel<ViewItem>{

	public HistoryViewModel() {
		super(new String[] {"Id", "Sale_ID", "Name", "Quantity", "Total", "Time"});
		}

	@Override
	protected Object getColumnValue(ViewItem item, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getTotal();
		case 5:
			return item.getTime();
		}
		return null;
	}
	
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final ViewItem item : rows) {
			
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getTotal() + "\t");
			buffer.append(item.getTime() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

		
}
