package cat.aubricoc.retailstore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.model.Product;

public class ProductsAdapter extends ArrayAdapter<Product> {

	public ProductsAdapter(Context context, List<Product> products) {
		super(context, R.layout.item_list_product, products);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = convertView;
		ProductViewHolder holder;
		Product product = getItem(position);
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.item_list_product, parent, false);
			holder = new ProductViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (ProductViewHolder) view.getTag();
		}

		if (product != null) {
			holder.name.setText(product.getName());
			holder.price.setText(DecimalFormat.getCurrencyInstance().format(product.getPrice()));
			Glide.with(getContext()).load(product.getImage()).into(holder.image);
		}
		return view;
	}

	private class ProductViewHolder {

		final ImageView image;

		final TextView name;

		final TextView price;

		ProductViewHolder(View view) {
			this.image = (ImageView) view.findViewById(R.id.product_image);
			this.name = (TextView) view.findViewById(R.id.product_name);
			this.price = (TextView) view.findViewById(R.id.product_price);
		}
	}
}
