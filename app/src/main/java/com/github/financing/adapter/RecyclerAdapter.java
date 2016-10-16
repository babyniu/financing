package com.github.financing.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.financing.R;
import com.github.financing.adapter.holder.MyViewHolder;
import com.github.financing.ui.MainActivity;

/**
 * Created by user on 2016/10/11.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Activity activity;
    public RecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_recycle_bid, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText("冠军稳赢");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
