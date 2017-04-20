package cat.aubricoc.retailstore.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.adapter.CartProductsAdapter;
import cat.aubricoc.retailstore.model.ItemCart;
import cat.aubricoc.retailstore.service.ProductService;

public class CartActivity extends AppCompatActivity {

	private List<ItemCart> cart = new ArrayList<>();

	private CartProductsAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_cart);

		ListView listView = (ListView) findViewById(R.id.cart_list);

		adapter = new CartProductsAdapter(this, cart);
		listView.setAdapter(adapter);

		adapter.setOnRemoveListener(new OnRemoveItemListener());

		fillCart();

		findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	private void fillCart() {
		ProductService productService = ProductService.newInstance(CartActivity.this);
		List<ItemCart> cart = productService.getCart();
		this.cart.clear();
		this.cart.addAll(cart);
		adapter.notifyDataSetChanged();

		Double total = 0d;
		for (ItemCart item : cart) {
			total += item.getQuantity() * item.getProduct().getPrice();
		}
		TextView totalView = (TextView) findViewById(R.id.cart_total);
		totalView.setText(DecimalFormat.getCurrencyInstance().format(total));
	}

	private class OnRemoveItemListener implements CartProductsAdapter.OnRemoveListener {

		@Override
		public void onRemove(ItemCart itemCart) {
			ProductService.newInstance(CartActivity.this).removeFromCart(itemCart);
			fillCart();
		}
	}
}
