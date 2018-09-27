package com.example.lorca.roomshareapp;

/**
 * Created by Richard on 19/04/2018.
 */

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RoomiesAdapter extends ArrayAdapter<User> {

    private Context mContext;
    private List<User> ulist = new ArrayList<>();

    public RoomiesAdapter(@NonNull Context context,  ArrayList<User> list) {
        super(context, 0 , list);
        mContext = context;
        ulist = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_roomies,parent,false);

        User currentUser = ulist.get(position);


        return listItem;
    }
}