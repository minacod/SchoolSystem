package com.example.android.schoolsystem.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.schoolsystem.R;
import com.example.android.schoolsystem.data.SystemContract.ClassRoomsEntry;
import com.example.android.schoolsystem.databinding.ClassRoomListItemBinding;
import com.example.android.schoolsystem.model.ClassRoom;

/**
 * Created by shafy on 14/12/2017.
 */

public class ClassRoomsListAdapter extends RecyclerView.Adapter<ClassRoomsListAdapter.ViewHolder>{

    private Cursor mClassRooms;

    public ClassRoomsListAdapter(Cursor mClassRooms) {
        this.mClassRooms = mClassRooms;
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
        mClassRooms.moveToPosition(position);
        holder.itemView.setTag(mClassRooms.getInt(mClassRooms.getColumnIndex(ClassRoomsEntry._ID)));
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mClassRooms.getCount();
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
            mClassRooms.moveToPosition(itemNumber);
            mBinding.tvCrName.setText(mClassRooms.getString(mClassRooms.getColumnIndex(ClassRoomsEntry.COL_ROOM_NAME)));
            mBinding.tvCrCap.setText(String.valueOf(mClassRooms.getInt(mClassRooms.getColumnIndex(ClassRoomsEntry.COL_ROOM_CAP))));
        }

    }
}
