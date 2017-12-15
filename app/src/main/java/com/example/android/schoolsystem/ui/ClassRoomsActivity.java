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
import com.example.android.schoolsystem.adapter.ClassRoomsListAdapter;
import com.example.android.schoolsystem.data.SystemContract.ClassRoomsEntry;
import com.example.android.schoolsystem.data.SystemDBHelper;
import com.example.android.schoolsystem.databinding.ActivityClassRoomsBinding;


public class ClassRoomsActivity extends AppCompatActivity {

    private ActivityClassRoomsBinding mBinding;
    private SQLiteDatabase mDb;
    private Cursor cursor;
    private  ClassRoomsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_rooms);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_class_rooms);
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
                String name= (String) viewHolder.itemView.getTag();
                int tasksDeleted = mDb.delete(ClassRoomsEntry.TABLE_NAME, ClassRoomsEntry.COL_ROOM_NAME+"=?", new String[]{name});
                updateUi();
            }
        }).attachToRecyclerView(mBinding.rvCrList);

    }
    void updateUi(){

        cursor = mDb.query(ClassRoomsEntry.TABLE_NAME,null,null,
                null,null,null,null);
        if(!cursor.moveToFirst()){
            mBinding.textView.setVisibility(View.VISIBLE);
            mBinding.rvCrList.setVisibility(View.INVISIBLE);
            return;
        }

        mBinding.textView.setVisibility(View.INVISIBLE);
        mBinding.rvCrList.setVisibility(View.VISIBLE);
        adapter = new ClassRoomsListAdapter(cursor);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mBinding.rvCrList.setAdapter(adapter);
        mBinding.rvCrList.setLayoutManager(manager);
        mBinding.rvCrList.setHasFixedSize(true);
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
        Intent i =new Intent(this,AddRoomActivity.class);
        startActivity(i);
    }
}
