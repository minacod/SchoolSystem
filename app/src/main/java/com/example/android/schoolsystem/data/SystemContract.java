package com.example.android.schoolsystem.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by shafy on 14/12/2017.
 */

public class SystemContract {
    public static final String AUTHORITY = "om.example.android.schoolsystem";

    public static class ClassRoomsEntry implements BaseColumns {
        public static final String TABLE_NAME="class_rooms";
        public static final String COL_ROOM_NAME="name";
        public static final String COL_ROOM_CAP="capacity";
    }
    public static class DepartmentsEntry implements BaseColumns {
        public static final String TABLE_NAME="departments";
        public static final String DEPARTMENT_NO="no";
        public static final String DEPARTMENT_NAME="name";
    }
    public static class CoursesEntry implements BaseColumns {
        public static final String TABLE_NAME="courses";
        public static final String COL_ID="id";
        public static final String COL_Name="name";
        public static final String COL_LVL_ID="lvl_id";
        public static final String COL_CLASS_ID="class_id";
    }
    public static class LevelsEntry implements BaseColumns {
        public static final String TABLE_NAME="levels";
        public static final String COL_ID="id";
        public static final String COL_Name="name";
        public static final String COL_DES="description";
    }


}
