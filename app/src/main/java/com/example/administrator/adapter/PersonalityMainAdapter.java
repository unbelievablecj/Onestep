package com.example.administrator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.model.Personality;

import java.util.List;

public class PersonalityMainAdapter extends RecyclerView.Adapter<PersonalityMainAdapter.ViewHolder> {
    private List<Personality>mPersonalitylist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView personalityview;
        TextView personalitytext;
        public ViewHolder(View view){
            super(view);
            personalityview=(ImageView)view.findViewById(R.id.peresonalitylistview);
            personalitytext=(TextView)view.findViewById(R.id.peresonalitylisttext);
        }
    }
    public PersonalityMainAdapter(List<Personality>personalityList){
        mPersonalitylist=personalityList;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.personalitylistitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        Personality personality=mPersonalitylist.get(position);
        holder.personalityview.setImageResource(personality.getPersonalityview());
        holder.personalitytext.setText(personality.getPersonalitytext());
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int posotion=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }
    @Override
    public int getItemCount(){
        return mPersonalitylist.size();
    }
}
