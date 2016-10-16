package com.github.financing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.financing.R;
import com.github.financing.adapter.RecyclerAdapter;
import com.github.financing.base.BaseFragment;

/********************************************
 * 作者：Administrator
 * 时间：2016/10/9
 * 描述：
 *******************************************/
public class ProductsFragment extends BaseFragment {
    private RecyclerAdapter recyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prodcuts, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerAdapter = new RecyclerAdapter(this.getActivity());
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }
}
