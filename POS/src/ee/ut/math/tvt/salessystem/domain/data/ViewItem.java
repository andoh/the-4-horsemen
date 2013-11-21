package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;


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
	private BigDecimal price;
	
	@Column(name = "quantity")
    private Integer quantity;
	
	@Column(name = "total")
    private BigDecimal total;
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
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
    
//	public Double getPrice() {
//		return stockItem.getPrice();
//	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

//	public String getName() {
//		return stockItem.getName();
//	}
	
	public String getName() {
		return name;
	}

	public ViewItem()
	{}

	
    @Override
	public String toString() {
		return "ViewItem [id=" + id + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", total=" + total + ", time="
				+ time + "]";
	}

	public ViewItem(Long id, String name, BigDecimal price, Integer quantity,
			BigDecimal total, String time) {
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

    
//    public StockItem getStockItem() {
//        return stockItem;
//    }
    

//    public void setStockItem(StockItem stockItem) {
//        this.stockItem = stockItem;
//    }
    
    
    public String getTime() {
    	return time;
    }
 
}
