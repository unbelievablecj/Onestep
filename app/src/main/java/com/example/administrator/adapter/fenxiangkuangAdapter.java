package com.example.administrator.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.model.fenxiangkuang;

import java.util.List;
//陈子恒，recyclerview分享框所需要的适配器
public class fenxiangkuangAdapter extends RecyclerView.Adapter<fenxiangkuangAdapter.ViewHolder> {
    private List<fenxiangkuang> mtest1List;
    private int flag;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private fenxiangkuangAdapter.OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(fenxiangkuangAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fengxiangview;
        ImageView touxiang;
        ImageView dianzan;
        ImageView pinglun;
        ImageView shoucan;
        ImageView ditu;
        TextView address;
        TextView name;
        TextView time;
        TextView dianzanshu;
        TextView pinglunshu;

        public ViewHolder(View view) {
            super(view);
            fengxiangview=view;
            ditu = (ImageView) view.findViewById(R.id.findtupian);
            touxiang = (ImageView) view.findViewById(R.id.touxiang);
            dianzan = (ImageView) view.findViewById(R.id.dianzan);
            pinglun = (ImageView) view.findViewById(R.id.pinglun);
            shoucan = (ImageView) view.findViewById(R.id.shoucan);
            address = (TextView) view.findViewById(R.id.address);
            name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.strategy_time);
            dianzanshu = (TextView) view.findViewById(R.id.dianzanshu);
            pinglunshu = (TextView) view.findViewById(R.id.pinglunshu);
        }
    }

    public fenxiangkuangAdapter(List<fenxiangkuang>test1List){
        mtest1List=test1List;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sharing_templet,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                fenxiangkuang fenxiangkuang=mtest1List.get(position);

            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        final fenxiangkuang fenxiangkuang=mtest1List.get(position);
        holder.touxiang.setImageResource(fenxiangkuang.getTouxiangid());
        final boolean[] ischeck = {fenxiangkuang.isZanFocus()};
        if (ischeck[0]){
            holder.dianzan.setImageResource(R.drawable.dianzan2);
        }else {
            holder.dianzan.setImageResource(R.drawable.dianzan1);
        }
        final boolean[] isscheck={fenxiangkuang.isShoucanFocus()};
        if (isscheck[0]){
            holder.shoucan.setImageResource(R.drawable.shoucang);
        }else {
            holder.shoucan.setImageResource(R.drawable.shoucan);
        }
        holder.pinglun.setImageResource(fenxiangkuang.getPinglunid());
        holder.ditu.setImageResource(fenxiangkuang.getDituid());
        holder.name.setText(fenxiangkuang.getName());
        holder.address.setText(fenxiangkuang.getAddress());
        holder.dianzanshu.setText(fenxiangkuang.getDianzan());
        holder.pinglunshu.setText(fenxiangkuang.getPinglun());
        holder.time.setText(fenxiangkuang.getTime());
        //item跳转
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int posotion=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        //框中点赞图标的点击事件
        holder.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (ischeck[0] ==true){
                holder.dianzan.setImageResource(R.drawable.dianzan1);
                int count;
                count=Integer.valueOf(fenxiangkuang.getDianzan());
//                count--;//正常来说从服务器得到的数据是实时更新的，点赞后会刷新数据，这里暂时没有所以先注释掉；
                holder.dianzanshu.setText(count+"");
                ischeck[0] =false;
            }else {
                holder.dianzan.setImageResource(R.drawable.dianzan2);
                int count;
                count=Integer.valueOf(fenxiangkuang.getDianzan());
                count++;
                holder.dianzanshu.setText(count+"");
                ischeck[0]=true;
            }
        }});
        //收藏图标的点击事件，暂时没有点了收藏就将此条目加入后端数据的逻辑
        holder.shoucan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isscheck[0]==true){
                    holder.shoucan.setImageResource(R.drawable.shoucan);
                    isscheck[0]=false;
                }else {
                    holder.shoucan.setImageResource(R.drawable.shoucang);
                    isscheck[0]=true;
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return mtest1List.size();
    }
    public void addItem(List<fenxiangkuang>newDatas){
        mtest1List.remove(mtest1List);
        mtest1List.addAll(newDatas);
        notifyDataSetChanged();
    }

}
