package com.github.financing.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.financing.R;

/********************************************
 * 作者：Administrator
 * 时间：2016/10/15
 * 描述：
 *******************************************/
public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView tv;
    public MyViewHolder(View itemView) {
        super(itemView);
        tv  = (TextView)itemView.findViewById(R.id.bid_title);

    }
}
