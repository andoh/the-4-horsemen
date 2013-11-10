package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "HistoryItem")
public class HistoryItem implements DisplayableItem {
	
	// Ei tea veel, mis siia tulema peaks
	private List<SoldItem> goods;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "sum")
	private Double sum;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
