package cat.aubricoc.retailstore.service;

import android.content.Context;

import cat.aubricoc.retailstore.dao.ItemCartDao;
import cat.aubricoc.retailstore.dao.ProductDao;
import cat.aubricoc.retailstore.model.ItemCart;
import cat.aubricoc.retailstore.model.Product;

public class ProductService {

	private Context context;

	public static ProductService newInstance(Context context) {
		return new ProductService(context);
	}

	private ProductService(Context context) {
		this.context = context;
	}

	public Product getById(Long productId) {
		Product product = ProductDao.newInstance(context).getById(productId);
		product.setItemCart(ItemCartDao.newInstance(context).getById(product));
		return product;
	}

	public void addToCart(Product product) {
		ItemCartDao itemCartDao = ItemCartDao.newInstance(context);
		ItemCart itemCart = product.getItemCart();
		if (itemCart == null) {
			itemCart = new ItemCart();
			itemCart.setProduct(product);
			itemCart.setQuantity(1);
			itemCartDao.create(itemCart);
			product.setItemCart(itemCart);
		} else {
			itemCart.setQuantity(itemCart.getQuantity() + 1);
			itemCartDao.update(itemCart);
		}
	}
}
