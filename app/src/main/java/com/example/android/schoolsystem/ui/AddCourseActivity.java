package com.example.android.schoolsystem.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.schoolsystem.R;
import com.example.android.schoolsystem.data.SystemContract;
import com.example.android.schoolsystem.data.SystemDBHelper;
import com.example.android.schoolsystem.databinding.ActivityAddCourseBinding;

public class AddCourseActivity extends AppCompatActivity {


    private SQLiteDatabase mDb;
    private Cursor cursor;
    private ActivityAddCourseBinding mBinding;
    private int classPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_course);
        SystemDBHelper helper = new SystemDBHelper(this);
        mDb = helper.getReadableDatabase();
        cursor = mDb.query(SystemContract.ClassRoomsEntry.TABLE_NAME,null,null,
                null,null,null,null);

        if(cursor.moveToFirst()){
            String[] tmp = new String[cursor.getCount()];
            int i =0;
            tmp[i]=cursor.getString(cursor.getColumnIndex(SystemContract.ClassRoomsEntry.COL_ROOM_NAME));
            while (cursor.moveToNext()){
                i++;
                tmp[i]=cursor.getString(cursor.getColumnIndex(SystemContract.ClassRoomsEntry.COL_ROOM_NAME));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tmp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mBinding.spinner.setAdapter(adapter);
            mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    classPosition=position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });
        }
        else {
            mBinding.spinner.setEnabled(false);
            mBinding.btnAddC.setEnabled(false);
            Toast.makeText(this,"Add Classes",Toast.LENGTH_SHORT).show();
        }
    }

    public void add(View view) {

    }
}
