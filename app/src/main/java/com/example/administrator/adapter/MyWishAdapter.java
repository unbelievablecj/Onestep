package com.example.administrator.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.model.MyWish;

import java.util.List;

public class MyWishAdapter extends RecyclerView.Adapter<MyWishAdapter.ViewHolder> {
    private List<MyWish> myWishList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView seriaNumber;
        TextView place;
        ImageView locationIcon;
        ImageView deleteIcon;

        public ViewHolder(View view) {
            super(view);
            seriaNumber = (TextView) view.findViewById(R.id.wish_num);
            place = (TextView)view.findViewById(R.id.wish_place);
            locationIcon = (ImageView)view.findViewById(R.id.location_icon);
            deleteIcon = (ImageView)view.findViewById(R.id.delete_wish_icon);
        }
    }

    public MyWishAdapter(List<MyWish>wishList) {
        myWishList = wishList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        MyWish myWish=myWishList.get(position);
        holder.place.setText(myWish.getPlace());
        holder.seriaNumber.setText(myWish.getSerialNumber());
        holder.locationIcon.setImageResource(myWish.getImageOneID());
        holder.deleteIcon.setImageResource(myWish.getImageTwoID());
    }
    @Override
    public int getItemCount(){
        return myWishList.size();
    }

}


