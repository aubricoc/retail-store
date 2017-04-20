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

import java.text.DecimalFormat;
import java.util.List;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.model.ItemCart;
import cat.aubricoc.retailstore.model.Product;

public class CartProductsAdapter extends ArrayAdapter<ItemCart> {

	private OnRemoveListener onRemoveListener;

	public CartProductsAdapter(Context context, List<ItemCart> products) {
		super(context, R.layout.item_list_cart, products);
	}

	public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
		this.onRemoveListener = onRemoveListener;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = convertView;
		ProductViewHolder holder;
		final ItemCart item = getItem(position);
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.item_list_cart, parent, false);
			holder = new ProductViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (ProductViewHolder) view.getTag();
		}

		if (item != null) {
			Product product = item.getProduct();
			holder.name.setText(product.getName());
			String price = DecimalFormat.getCurrencyInstance().format(product.getPrice()) + " x" + item.getQuantity();
			holder.price.setText(price);
			holder.total.setText(DecimalFormat.getCurrencyInstance().format(product.getPrice() * item.getQuantity()));
			holder.remove.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (onRemoveListener != null) {
						onRemoveListener.onRemove(item);
					}
				}
			});
		}
		return view;
	}

	public interface OnRemoveListener {
		void onRemove(ItemCart itemCart);
	}

	private class ProductViewHolder {

		final TextView name;

		final TextView price;

		final TextView total;

		final ImageView remove;

		ProductViewHolder(View view) {
			this.name = (TextView) view.findViewById(R.id.product_name);
			this.price = (TextView) view.findViewById(R.id.product_price);
			this.total = (TextView) view.findViewById(R.id.product_total);
			this.remove = (ImageView) view.findViewById(R.id.remove_from_cart);
		}
	}
}
