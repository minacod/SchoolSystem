package com.example.android.schoolsystem.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.android.schoolsystem.R;
import com.example.android.schoolsystem.adapter.DepartmentsListAdapter;
import com.example.android.schoolsystem.data.SystemContract;
import com.example.android.schoolsystem.data.SystemDBHelper;
import com.example.android.schoolsystem.databinding.ActivityDepartmentsBinding;

public class DepartmentsActivity extends AppCompatActivity {

    private ActivityDepartmentsBinding mBinding;
    private SQLiteDatabase mDb;
    private Cursor cursor;
    private DepartmentsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_departments);
        SystemDBHelper helper = new SystemDBHelper(this);
        mDb = helper.getReadableDatabase();

        updateUi();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id= (int) viewHolder.itemView.getTag();
                int tasksDeleted = mDb.delete(SystemContract.DepartmentsEntry.TABLE_NAME,
                        SystemContract.DepartmentsEntry.DEPARTMENT_NO+"=?",
                        new String[]{String.valueOf(id)});
                updateUi();
            }
        }).attachToRecyclerView(mBinding.rvDList);

    }
    void updateUi(){

        cursor = mDb.query(SystemContract.DepartmentsEntry.TABLE_NAME,null,null,
                null,null,null,null);
        if(!cursor.moveToFirst()){
            mBinding.textView.setVisibility(View.VISIBLE);
            mBinding.rvDList.setVisibility(View.INVISIBLE);
            return;
        }

        mBinding.textView.setVisibility(View.INVISIBLE);
        mBinding.rvDList.setVisibility(View.VISIBLE);
        adapter = new DepartmentsListAdapter(cursor);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mBinding.rvDList.setAdapter(adapter);
        mBinding.rvDList.setLayoutManager(manager);
        mBinding.rvDList.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
    }

    public void openAdd(View view) {
        Intent i =new Intent(this,AddDepartmentActivity.class);
        startActivity(i);
    }
}
