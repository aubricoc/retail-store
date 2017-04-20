package cat.aubricoc.retailstore.model;

import com.canteratech.apa.annotation.Entity;
import com.canteratech.apa.annotation.GeneratedValue;
import com.canteratech.apa.annotation.Id;
import com.canteratech.apa.annotation.Transient;

import java.util.List;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private byte[] icon;

	@Transient
	private List<Product> products;

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

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
