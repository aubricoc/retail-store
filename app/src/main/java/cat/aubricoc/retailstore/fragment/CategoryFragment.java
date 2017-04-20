package cat.aubricoc.retailstore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.adapter.ProductsAdapter;
import cat.aubricoc.retailstore.model.Category;
import cat.aubricoc.retailstore.service.CategoryService;

public class CategoryFragment extends Fragment {

	private static final String ARG_CATEGORY_ID = "categoryId";

	public CategoryFragment() {
	}

	public static CategoryFragment newInstance(Category category) {
		CategoryFragment fragment = new CategoryFragment();
		Bundle args = new Bundle();
		args.putLong(ARG_CATEGORY_ID, category.getId());
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Long categoryId = getArguments().getLong(ARG_CATEGORY_ID);
		Category category = CategoryService.newInstance(getContext()).getById(categoryId);

		ListView listView = (ListView) inflater.inflate(R.layout.fragment_category, container, false);
		listView.setAdapter(new ProductsAdapter(getContext(), category.getProducts()));

		return listView;
	}
}
