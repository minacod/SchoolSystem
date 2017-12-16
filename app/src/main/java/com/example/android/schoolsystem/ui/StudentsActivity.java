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
import com.example.android.schoolsystem.adapter.StudentListAdapter;
import com.example.android.schoolsystem.data.SystemContract;
import com.example.android.schoolsystem.data.SystemDBHelper;
import com.example.android.schoolsystem.databinding.ActivityStudentsBinding;

public class StudentsActivity extends AppCompatActivity {

    private ActivityStudentsBinding mBinding;
    private SQLiteDatabase mDb;
    private Cursor cursor;
    private StudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_students);
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
                int tasksDeleted = mDb.delete(SystemContract.StudentsEntry.TABLE_NAME,
                        SystemContract.StudentsEntry._ID+"=?",
                        new String[]{String.valueOf(id)});
                updateUi();
            }
        }).attachToRecyclerView(mBinding.rvSList);

    }
    void updateUi(){

        cursor = mDb.query(SystemContract.StudentsEntry.TABLE_NAME,null,null,
                null,null,null,null);
        if(!cursor.moveToFirst()){
            mBinding.textView.setVisibility(View.VISIBLE);
            mBinding.rvSList.setVisibility(View.INVISIBLE);
            return;
        }

        mBinding.textView.setVisibility(View.INVISIBLE);
        mBinding.rvSList.setVisibility(View.VISIBLE);
        adapter = new StudentListAdapter(cursor);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mBinding.rvSList.setAdapter(adapter);
        mBinding.rvSList.setLayoutManager(manager);
        mBinding.rvSList.setHasFixedSize(true);
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
        Intent i = new Intent(this,AddStudentActivity.class);
        startActivity(i);
    }
}
