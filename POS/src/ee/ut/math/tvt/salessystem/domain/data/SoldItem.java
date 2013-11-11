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
public class SoldItem implements Cloneable, DisplayableItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sale_id")
	private int sale_id;
	
	@Transient
	private StockItem stockItem;
    	
	@Column(name = "quantity")
    private Integer quantity;
	
	@Column(name = "total")
	private Double sum;
	@Transient
	private String name;
	@Transient
	private Double price;

    public Double getPrice() {
		return stockItem.getPrice();
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public String getName() {
		return stockItem.getName();
	}



	public SoldItem(StockItem stockItem, int quantity) {
        this.stockItem = stockItem;
        this.id = stockItem.getId();
        this.quantity = quantity;
        this.sum = quantity*stockItem.getPrice();
        
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
    
    public int getSale_id() {
		return sale_id;
	}


	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}


	public Double getSum() {
		// TODO Auto-generated method stub
		return this.sum;
	}

 
}
