package cat.aubricoc.retailstore.model;

import com.canteratech.apa.annotation.Entity;
import com.canteratech.apa.annotation.Id;

@Entity
public class ItemCart {

	@Id
	private Product product;

	private Integer quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
