package com.example.mahmud.digitedu;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by mahmud on 9/4/16.
 */
public class ResultActivity extends ListActivity {

    SimpleCursorAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        if( getActionBar() != null){
            getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e91e63")));
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
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

}
