package com.example.android.schoolsystem.ui;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.schoolsystem.R;
import com.example.android.schoolsystem.data.SystemContract;
import com.example.android.schoolsystem.data.SystemDBHelper;
import com.example.android.schoolsystem.databinding.ActivityAddRoomBinding;
import com.example.android.schoolsystem.databinding.ActivityClassRoomsBinding;

public class AddRoomActivity extends AppCompatActivity {

    ActivityAddRoomBinding mBinding;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_room);
        SystemDBHelper helper = new SystemDBHelper(this);
        mDb = helper.getWritableDatabase();
    }

    public void add(View view) {
        String name = mBinding.editText.getText().toString();
        String tmp = mBinding.editText2.getText().toString();
        if(name.equals("")||tmp.equals(""))
            Toast.makeText(this,"Check Your Inputs",Toast.LENGTH_SHORT).show();
        else {
            int cap = Integer.parseInt(tmp);
            ContentValues cv = new ContentValues();
            cv.put(SystemContract.ClassRoomsEntry.COL_ROOM_NAME,name);
            cv.put(SystemContract.ClassRoomsEntry.COL_ROOM_CAP, cap);
            mDb.insert(SystemContract.ClassRoomsEntry.TABLE_NAME,null,cv);
            finish();
        }
    }
}
