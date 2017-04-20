package cat.aubricoc.retailstore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.model.Product;
import cat.aubricoc.retailstore.service.ProductService;
import cat.aubricoc.retailstore.utils.Constants;

public class ProductDetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_product_detail);

		Long productId = getIntent().getLongExtra(Constants.ARG_PRODUCT_ID, 0);
		final Product product = ProductService.newInstance(this).getById(productId);

		TextView name = (TextView) findViewById(R.id.product_name);
		TextView price = (TextView) findViewById(R.id.product_price);
		ImageView image = (ImageView) findViewById(R.id.product_image);
		final TextView inCart = (TextView) findViewById(R.id.in_cart);

		name.setText(product.getName());
		price.setText(DecimalFormat.getCurrencyInstance().format(product.getPrice()));
		Glide.with(this).load(product.getImage()).into(image);
		inCart.setText(product.getItemCart() == null ? "0" : product.getItemCart().getQuantity().toString());

		findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ProductService.newInstance(ProductDetailActivity.this).addToCart(product);
				inCart.setText(product.getItemCart() == null ? "0" : product.getItemCart().getQuantity().toString());
			}
		});

		findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
}
