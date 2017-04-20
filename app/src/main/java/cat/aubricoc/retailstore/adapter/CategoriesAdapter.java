package cat.aubricoc.retailstore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cat.aubricoc.retailstore.fragment.CategoryFragment;
import cat.aubricoc.retailstore.model.Category;

public class CategoriesAdapter extends FragmentPagerAdapter {

	private final List<Category> categories;

	public CategoriesAdapter(FragmentManager fm, List<Category> categories) {
		super(fm);
		this.categories = categories;
	}

	@Override
	public Fragment getItem(int position) {
		return CategoryFragment.newInstance(categories.get(position));
	}

	@Override
	public int getItemPosition(Object object) {
		if (object instanceof Category) {
			return categories.indexOf(object);
		}
		return -1;
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return categories.get(position).getName();
	}
}
