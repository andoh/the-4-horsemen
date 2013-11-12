package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class HistoryViewModel extends SalesSystemTableModel<SoldItem>{

	public HistoryViewModel() {
		super(new String[] {"Id", "Sale_ID", "Name", "Quantity", "Total", "Time"});
		}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	
}
