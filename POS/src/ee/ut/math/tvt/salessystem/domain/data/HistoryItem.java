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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DATE")
	private String date;
	
	@Column(name = "TIME")
	private String time;
	
	@Column(name = "TOTAL")
	private Double sum;
	
	
	public HistoryItem(){
		
	}
	public HistoryItem(Long id, String date, String time, Double sum) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.sum = sum;
		}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
}
