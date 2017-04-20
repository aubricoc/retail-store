package cat.aubricoc.retailstore.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.canteratech.apa.DatabaseReflection;

import java.util.List;

import cat.aubricoc.retailstore.model.Category;
import cat.aubricoc.retailstore.model.ItemCart;
import cat.aubricoc.retailstore.model.Product;
import cat.aubricoc.retailstore.utils.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		List<String> createTables = DatabaseReflection.getInstance().prepareCreateTables(Product.class, Category.class, ItemCart.class);
		for (String sql : createTables) {
			db.execSQL(sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sv, int oldVersion, int newVersion) {

	}
}
