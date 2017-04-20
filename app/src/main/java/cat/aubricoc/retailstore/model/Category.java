package cat.aubricoc.retailstore.model;

import com.canteratech.apa.annotation.Entity;
import com.canteratech.apa.annotation.GeneratedValue;
import com.canteratech.apa.annotation.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private byte[] icon;

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
}
