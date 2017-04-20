package cat.aubricoc.retailstore.service;

import android.content.Context;

import java.util.List;

import cat.aubricoc.retailstore.dao.CategoryDao;
import cat.aubricoc.retailstore.dao.ProductDao;
import cat.aubricoc.retailstore.model.Category;

public class CategoryService {
	
	private Context context;
	
	public static CategoryService newInstance(Context context) {
		return new CategoryService(context);
	}
	
	private CategoryService(Context context) {
		this.context = context;
	}

	public Category getById(Long categoryId) {
		Category category = CategoryDao.newInstance(context).getById(categoryId);
		category.setProducts(ProductDao.newInstance(context).getByCategory(categoryId));
		return category;
	}

	public List<Category> getAll() {
		return CategoryDao.newInstance(context).getAll();
	}
}
