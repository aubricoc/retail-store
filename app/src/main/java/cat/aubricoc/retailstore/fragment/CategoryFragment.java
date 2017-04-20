package cat.aubricoc.retailstore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.activity.ProductDetailActivity;
import cat.aubricoc.retailstore.adapter.ProductsAdapter;
import cat.aubricoc.retailstore.model.Category;
import cat.aubricoc.retailstore.model.Product;
import cat.aubricoc.retailstore.service.CategoryService;
import cat.aubricoc.retailstore.utils.Constants;

public class CategoryFragment extends Fragment {

	public static CategoryFragment newInstance(Category category) {
		CategoryFragment fragment = new CategoryFragment();
		Bundle args = new Bundle();
		args.putLong(Constants.ARG_CATEGORY_ID, category.getId());
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Long categoryId = getArguments().getLong(Constants.ARG_CATEGORY_ID);
		Category category = CategoryService.newInstance(getContext()).getById(categoryId);

		ListView listView = (ListView) inflater.inflate(R.layout.fragment_category, container, false);
		listView.setAdapter(new ProductsAdapter(getContext(), category.getProducts()));

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Product product = (Product) adapterView.getItemAtPosition(position);
				Intent intent = new Intent(getContext(), ProductDetailActivity.class);
				intent.putExtra(Constants.ARG_PRODUCT_ID, product.getId());
				getContext().startActivity(intent);
			}
		});

		return listView;
	}
}
