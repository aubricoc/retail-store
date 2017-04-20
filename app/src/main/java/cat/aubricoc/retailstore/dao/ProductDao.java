package cat.aubricoc.retailstore.dao;

import android.content.Context;

import com.canteratech.apa.Dao;

import cat.aubricoc.retailstore.db.DatabaseHelper;
import cat.aubricoc.retailstore.model.Product;

public class ProductDao extends Dao<Product, Long> {

	private ProductDao(Context context) {
		super(new DatabaseHelper(context), Product.class);
	}

	public static ProductDao newInstance(Context context) {
		return new ProductDao(context);
	}
}
