package com.example.android.schoolsystem.model;

import java.io.Serializable;

/**
 * Created by shafy on 14/12/2017.
 */

public class ClassRoom implements Serializable {
    private String mName;
    private String mCapacity;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCapacity() {
        return mCapacity;
    }

    public void setmCapacity(String mCapacity) {
        this.mCapacity = mCapacity;
    }

    public ClassRoom(String mName, String mCapacity) {
        this.mName = mName;
        this.mCapacity = mCapacity;
    }
}
