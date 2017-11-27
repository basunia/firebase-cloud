package com.example.mahmud.digitedu.activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import com.example.mahmud.digitedu.MainActivity;
import com.example.mahmud.digitedu.utils.DatabaseConstants;
import com.example.mahmud.digitedu.data.DatabaseHelper;
import com.example.mahmud.digitedu.R;

/**
 * Created by mahmud on 9/4/16.
 */
public class ResultActivity extends ListActivity {

    SimpleCursorAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatCallback callback = new AppCompatCallback() {
            @Override
            public void onSupportActionModeStarted(ActionMode actionMode) {
            }

            @Override
            public void onSupportActionModeFinished(ActionMode actionMode) {
            }

            @Nullable
            @Override
            public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
                return null;
            }
        };

        AppCompatDelegate delegate = AppCompatDelegate.create(this, callback);
        delegate.onCreate(savedInstanceState);

        delegate.setContentView(R.layout.result_parent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        delegate.setSupportActionBar(toolbar);
        try {
            delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
            //getSupportActionBar().setIcon(R.drawable.icon2);
            delegate.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

//        TextView tv = (TextView) findViewById(R.id.message);
//        DatabaseHelper databaseHelper = new DatabaseHelper(ResultActivity.this);
//        String txt = databaseHelper.getAppCategoryDetail();
//
//        if(txt != null) {
//            tv.setText(""+ txt);
//        }

        DatabaseHelper databaseHelper = new DatabaseHelper(ResultActivity.this);
//        String txt = databaseHelper.getAppCategoryDetail();
        Cursor cursor =  databaseHelper.getCursor();
        startManagingCursor(cursor);

        mAdapter = new SimpleCursorAdapter(this,
                R.layout.container_list_item_view, cursor,
                new String[] { DatabaseConstants.COL_LANG_NAME, DatabaseConstants.TIME_STAMP },
                new int[] { R.id.list_item, R.id.stamp });

        this.setListAdapter(mAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.back:
                finish();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

}
