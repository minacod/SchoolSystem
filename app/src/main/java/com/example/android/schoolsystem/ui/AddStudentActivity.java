package com.example.android.schoolsystem.ui;

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
import com.example.android.schoolsystem.databinding.ActivityAddStudentBinding;

public class AddStudentActivity extends AppCompatActivity {

    ActivityAddStudentBinding mBinding;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_student);
        SystemDBHelper helper = new SystemDBHelper(this);
        mDb = helper.getWritableDatabase();
    }

    public void add(View view) {
        String name = mBinding.etSName.getText().toString();
        String address = mBinding.etSAddress.getText().toString();
        String dob = mBinding.etSDob.getText().toString();
        String email = mBinding.etSEmail.getText().toString();
        if(name.equals("")||address.equals("")||dob.equals("")||email.equals(""))
            Toast.makeText(this,"Check Your Inputs",Toast.LENGTH_SHORT).show();
        else {
            ContentValues cv = new ContentValues();
            cv.put(SystemContract.StudentsEntry.COL_NAME, name);
            cv.put(SystemContract.StudentsEntry.COL_ADDRESS, address);
            cv.put(SystemContract.StudentsEntry.COL_DOB ,dob);
            cv.put(SystemContract.StudentsEntry.COL_EMAIL, email);
            mDb.insert(SystemContract.StudentsEntry.TABLE_NAME, null, cv);
            finish();
        }
    }
}
