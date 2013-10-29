package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

public class HistoryItem implements DisplayableItem {
	private List<SoldItem> goods;
	

	private String date;
	private String time;
	private Double sum;
	private long id;

	public HistoryItem(List<SoldItem> goods, String date, String time, Double sum) {
		this.goods = goods;
		this.date = date;
		this.time = time;
		this.sum = sum;
		this.id = 0;
	}
	
	public Long getId() {
		return id;
	}
	
	public List<SoldItem> getGoods() {
		return goods;
	}

	public void setGoods(List<SoldItem> goods) {
		this.goods = goods;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public void setId(long id) {
		this.id = id;
	}
}
