package cat.aubricoc.retailstore.dao;

import android.content.Context;

import com.canteratech.apa.Dao;

import cat.aubricoc.retailstore.db.DatabaseHelper;
import cat.aubricoc.retailstore.model.Category;

public class CategoryDao extends Dao<Category, Long> {

	private CategoryDao(Context context) {
		super(new DatabaseHelper(context), Category.class);
	}

	public static CategoryDao newInstance(Context context) {
		return new CategoryDao(context);
	}
}
