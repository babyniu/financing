package com.github.financing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.financing.R;
import com.github.financing.adapter.LoopPagerAdapter;
import com.github.financing.adapter.RecyclerAdapter;
import com.github.financing.base.BaseFragment;
import com.github.financing.views.RollPagerView;

/********************************************
 * 作者：Administrator
 * 时间：2016/10/9
 * 描述：
 *******************************************/
public class IndexFragment extends BaseFragment{
    private RecyclerAdapter recyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        RollPagerView rollPager = (RollPagerView) view.findViewById(R.id.roll_pager);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.index_recycler);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        rollPager.setAnimationDurtion(500);
        rollPager.setAdapter(new MyRollPagerAdapter(rollPager));
        return view;
    }

    private class MyRollPagerAdapter extends LoopPagerAdapter{

        public MyRollPagerAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        private int[] img = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};
        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(img[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            return imageView;
        }

        @Override
        public int getRealCount() {
            return img.length;
        }
    }
}
