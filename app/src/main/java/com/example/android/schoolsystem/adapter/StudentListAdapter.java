package com.example.android.schoolsystem.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.schoolsystem.data.SystemContract;
import com.example.android.schoolsystem.databinding.ClassRoomListItemBinding;

/**
 * Created by shafy on 16/12/2017.
 */

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder>{
    private Cursor mCursor;

    public StudentListAdapter(Cursor mClassRooms) {
        this.mCursor = mClassRooms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ClassRoomListItemBinding binding = ClassRoomListItemBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.itemView.setTag(mCursor.getInt(mCursor.getColumnIndex(SystemContract.StudentsEntry._ID)));
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ClassRoomListItemBinding mBinding;

        public ViewHolder(ClassRoomListItemBinding binding) {
            super(binding.getRoot());
            mBinding=binding;
        }

        public void bind(int itemNumber){
            if(itemNumber==0)
                mBinding.clCrListItem.setPadding(0,16,0,0);
            mCursor.moveToPosition(itemNumber);
            mBinding.tvCrName.setText(mCursor.getString(mCursor.getColumnIndex(SystemContract.StudentsEntry.COL_NAME)));
        }

    }
}
