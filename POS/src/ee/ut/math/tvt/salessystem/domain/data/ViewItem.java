package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history. 
 */
@Entity
@Table(name = "SoldItem")
public class ViewItem implements Cloneable, DisplayableItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "quantity")
    private Integer quantity;
	
	@Column(name = "total")
    private Double total;
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTime(String time) {
		this.time = time;
	}


	@Column(name = "datestamp")
	private String time;
	
	@Transient
	private StockItem stockItem;
    
	public Double getPrice() {
		return stockItem.getPrice();
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return stockItem.getName();
	}

	public ViewItem()
	{}

	
    public ViewItem(Long id, String name, Double price, Integer quantity,
			Double total, String time) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
		this.time = time;
	}

	public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }
    
    
    public String getTime() {
    	return time;
    }
 
}
