package cat.aubricoc.retailstore.service;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonToken;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.dao.CategoryDao;
import cat.aubricoc.retailstore.dao.ProductDao;
import cat.aubricoc.retailstore.model.Category;
import cat.aubricoc.retailstore.model.Product;

public class DataService {

	private Context context;

	public static DataService newInstance(Context context) {
		return new DataService(context);
	}

	private DataService(Context context) {
		this.context = context;
	}

	public void populateInitialData() {

		List<Category> categories;

		InputStream inputStream = context.getResources().openRawResource(R.raw.data);
		try {
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
			categories = readCategories(reader);
			reader.close();
		} catch (java.io.IOException e) {
			throw new IllegalStateException("Error reading data file", e);
		}

		CategoryDao categoryDao = CategoryDao.newInstance(context);
		ProductDao productDao = ProductDao.newInstance(context);
		for (Category category : categories) {
			categoryDao.create(category);
			for (Product product : category.getProducts()) {
				product.setCategory(category);
				productDao.create(product);
			}
		}
	}

	private List<Category> readCategories(JsonReader reader) throws IOException {
		List<Category> categories = new ArrayList<>();
		reader.beginArray();
		while (reader.hasNext()) {
			categories.add(readCategory(reader));
		}
		reader.endArray();
		return categories;
	}

	public Category readCategory(JsonReader reader) throws IOException {
		Category category = new Category();
		String icon = null;

		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("name")) {
				category.setName(reader.nextString());
			} else if (name.equals("icon")) {
				icon = reader.nextString();
			} else if (name.equals("products")) {
				category.setProducts(readProducts(reader));
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();

		if (icon != null) {
			category.setIcon(readFile(icon));
		}

		return category;
	}

	private List<Product> readProducts(JsonReader reader) throws IOException {
		List<Product> products = new ArrayList<>();

		reader.beginArray();
		while (reader.hasNext()) {
			products.add(readProduct(reader));
		}
		reader.endArray();
		return products;

	}

	private Product readProduct(JsonReader reader) throws IOException {
		Product product = new Product();
		String image = null;

		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("name")) {
				product.setName(reader.nextString());
			} else if (name.equals("image")) {
				image = reader.nextString();
			} else if (name.equals("price")) {
				product.setPrice(reader.nextDouble());
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();

		if (image != null) {
			product.setImage(readFile(image));
		}

		return product;
	}

	private byte[] readFile(String name) throws IOException {
		name = name.substring(0, name.lastIndexOf("."));
		System.out.println(name);
		int identifier = context.getResources().getIdentifier(name, "raw", context.getPackageName());
		if (identifier == 0) {
			return null;
		}
		InputStream inputStream = context.getResources().openRawResource(identifier);
		byte[] file = toByteArray(inputStream);
		inputStream.close();
		return file;
	}

	private static byte[] toByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[10240];
		int i;
		while ((i = is.read(buff, 0, buff.length)) > 0) {
			baos.write(buff, 0, i);
		}
		return baos.toByteArray();
	}
}
