package cat.aubricoc.retailstore.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.aubricoc.retailstore.R;
import cat.aubricoc.retailstore.adapter.CategoriesAdapter;
import cat.aubricoc.retailstore.fragment.CategoryFragment;
import cat.aubricoc.retailstore.model.Category;
import cat.aubricoc.retailstore.model.Product;
import cat.aubricoc.retailstore.service.CategoryService;
import cat.aubricoc.retailstore.service.DataService;
import cat.aubricoc.retailstore.service.PreferenceService;
import cat.aubricoc.retailstore.utils.Constants;

public class ProductsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private ViewPager viewPager;

	private CategoriesAdapter categoriesAdapter;

	private NavigationView navigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);

		PreferenceService preferenceService = PreferenceService.newInstance(this);
		if (preferenceService.isFirstTime()) {
			DataService.newInstance(this).populateInitialData();
			preferenceService.markLaunched();
		}

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		List<Category> categories = CategoryService.newInstance(this).getAll();
		categoriesAdapter = new CategoriesAdapter(getSupportFragmentManager(), categories);

		viewPager = (ViewPager) findViewById(R.id.container);
		viewPager.setAdapter(categoriesAdapter);
		viewPager.addOnPageChangeListener(new OnPageChangeListener());

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);

		prepareNavigationMenu(categories);
	}

	private void prepareNavigationMenu(final List<Category> categories) {
		MenuItem item = navigationView.getMenu().getItem(0);
		Menu menu = item.getSubMenu();
		for (final Category category : categories) {
			final MenuItem menuItem = menu.add(123, Menu.NONE, Menu.NONE, category.getName());
			Glide.with(this).load(category.getIcon()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
				@Override
				public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
					menuItem.setIcon(new BitmapDrawable(getResources(), resource));
				}
			});
			menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem menuItem) {
					int position = categoriesAdapter.getItemPosition(category);
					viewPager.setCurrentItem(position, true);
					return false;
				}
			});
		}
		menu.setGroupCheckable(123, true, true);
		menu.getItem(0).setChecked(true);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.cart_menu) {
			Intent intent = new Intent(this, ProductDetailActivity.class);
			startActivity(intent);
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		return onOptionsItemSelected(item);
	}

	private class OnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

		@Override
		public void onPageSelected(int position) {
			Menu menu = navigationView.getMenu().getItem(0).getSubMenu();
			for (int i = 0; i < menu.size(); i++) {
				menu.getItem(i).setChecked(i == position);
			}
		}
	}
}
