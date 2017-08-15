package com.example.mahmud.digitedu.activities;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.mahmud.digitedu.provider.LanguageContentProvider;
import com.example.mahmud.digitedu.R;
import com.example.mahmud.digitedu.utils.DatabaseConstants;

public class LanguageListActivity extends ListActivity implements
		LoaderCallbacks<Cursor> {

	private static final int LOADER_ID = 42;
	private CursorAdapter _adapter;

	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container_list);

		_adapter = new SimpleCursorAdapter(this,
				R.layout.container_list_item_view, null,
				new String[] { DatabaseConstants.COL_LANG_NAME },
				new int[] { R.id.list_item });

		setListAdapter(_adapter);

		getLoaderManager().initLoader(LOADER_ID, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		if (id != LOADER_ID) {
			return null;
		}
		return new CursorLoader(LanguageListActivity.this,
				LanguageContentProvider.CONTENT_URI,
				new String[] { DatabaseConstants.COL_LANG_ID, DatabaseConstants.COL_LANG_NAME }, null, null,
				null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		_adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		_adapter.swapCursor(null);
	}
}
