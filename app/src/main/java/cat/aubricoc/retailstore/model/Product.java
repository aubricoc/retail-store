package cat.aubricoc.retailstore.model;

import com.canteratech.apa.annotation.Entity;
import com.canteratech.apa.annotation.GeneratedValue;
import com.canteratech.apa.annotation.Id;
import com.canteratech.apa.annotation.Transient;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Double price;

	private byte[] image;

	private Category category;

	@Transient
	private ItemCart itemCart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ItemCart getItemCart() {
		return itemCart;
	}

	public void setItemCart(ItemCart itemCart) {
		this.itemCart = itemCart;
	}
}
