package cat.aubricoc.retailstore.service;

import android.content.Context;

public class DataService {

	private Context context;

	public static DataService newInstance(Context context) {
		return new DataService(context);
	}

	private DataService(Context context) {
		this.context = context;
	}

	public void populateInitialData() {

	}
}
