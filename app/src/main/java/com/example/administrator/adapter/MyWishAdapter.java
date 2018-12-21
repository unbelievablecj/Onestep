package com.example.administrator.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.model.MyWish;

import java.util.List;

public class MyWishAdapter extends RecyclerView.Adapter<MyWishAdapter.ViewHolder> {
    private List<MyWish> myWishList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        View myWishView;
        TextView seriaNumber;
        TextView place;
        ImageView locationIcon;
        ImageView deleteIcon;

        public ViewHolder(View view) {
            super(view);
            myWishView = view;
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
        final ViewHolder holder=new ViewHolder(view);
        holder.myWishView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MyWish myWish = myWishList.get(position);

            }
        });
        holder.locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MyWish myWish = myWishList.get(position);

            }
        });
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MyWish myWish = myWishList.get(position);
                myWishList.remove(position);
                notifyItemRemoved(position);//刷新被删除的地方
                notifyItemRangeChanged(position, getItemCount()); //刷新被删除数据，以及其后面的数据
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        MyWish myWish=myWishList.get(position);
        holder.place.setText(myWish.getPlace());
        holder.seriaNumber.setText(myWish.getSerialNumber());
//        holder.locationIcon.setImageResource(R.drawable.locationicon);
//        holder.deleteIcon.setImageResource(R.drawable.deleteicon);
    }
    @Override
    public int getItemCount(){
        return myWishList.size();
    }

}


