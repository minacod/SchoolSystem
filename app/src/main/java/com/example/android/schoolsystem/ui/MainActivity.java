package com.example.android.schoolsystem.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.schoolsystem.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void classRooms(View view) {
        Intent i = new Intent(this, ClassRoomsActivity.class);
        startActivity(i);
    }
    public void departments(View view) {
        Intent i = new Intent(this, DepartmentsActivity.class);
        startActivity(i);
    }

    public void courses(View view) {
        Intent i = new Intent(this, CoursesActivity.class);
        startActivity(i);
    }

    public void students(View view) {
        Intent i = new Intent(this, StudentsActivity.class);
        startActivity(i);
    }
}
