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
import com.example.android.schoolsystem.adapter.CoursesListAdapter;
import com.example.android.schoolsystem.adapter.DepartmentsListAdapter;
import com.example.android.schoolsystem.data.SystemContract;
import com.example.android.schoolsystem.data.SystemDBHelper;
import com.example.android.schoolsystem.databinding.ActivityAddCourseBinding;
import com.example.android.schoolsystem.databinding.ActivityCoursesBinding;

public class CoursesActivity extends AppCompatActivity {

    private ActivityCoursesBinding mBinding;
    private SQLiteDatabase mDb;
    private Cursor cursor;
    private CoursesListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_courses);
        SystemDBHelper helper = new SystemDBHelper(this);
        mDb = helper.getReadableDatabase();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id= (int) viewHolder.itemView.getTag();
                int tasksDeleted = mDb.delete(SystemContract.CoursesEntry.TABLE_NAME,
                        SystemContract.CoursesEntry.COL_ID+"=?",
                        new String[]{String.valueOf(id)});
                updateUi();
            }
        }).attachToRecyclerView(mBinding.rvCList);
    }
    void updateUi(){

        cursor = mDb.query(SystemContract.CoursesEntry.TABLE_NAME,null,null,
                null,null,null,null);
        if(!cursor.moveToFirst()){
            mBinding.textView.setVisibility(View.VISIBLE);
            mBinding.rvCList.setVisibility(View.INVISIBLE);
            return;
        }

        mBinding.textView.setVisibility(View.INVISIBLE);
        mBinding.rvCList.setVisibility(View.VISIBLE);
        adapter = new CoursesListAdapter(cursor);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mBinding.rvCList.setAdapter(adapter);
        mBinding.rvCList.setLayoutManager(manager);
        mBinding.rvCList.setHasFixedSize(true);
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
        Intent i =new Intent(this,AddCourseActivity.class);
        startActivity(i);
    }
}
