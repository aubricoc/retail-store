package cat.aubricoc.retailstore.dao;

import android.content.Context;

import com.canteratech.apa.Dao;

import cat.aubricoc.retailstore.db.DatabaseHelper;
import cat.aubricoc.retailstore.model.ItemCart;
import cat.aubricoc.retailstore.model.Product;

public class ItemCartDao extends Dao<ItemCart, Product> {

	private ItemCartDao(Context context) {
		super(new DatabaseHelper(context), ItemCart.class);
	}

	public static ItemCartDao newInstance(Context context) {
		return new ItemCartDao(context);
	}
}
