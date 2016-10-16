package com.github.financing.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.financing.R;
import com.github.financing.adapter.LoopPagerAdapter;
import com.github.financing.adapter.RecyclerAdapter;
import com.github.financing.base.BaseFragment;
import com.github.financing.views.loopPage.RollPagerView;
import com.github.financing.views.scrollText.AutoVerticalScrollTextView;

/********************************************
 * 作者：Administrator
 * 时间：2016/10/9
 * 描述：
 *******************************************/
public class IndexFragment extends BaseFragment{
    private RecyclerAdapter recyclerAdapter;
    private AutoVerticalScrollTextView scrollText;
    private String[] strings = {"xxxxxxxxxx","yyyyyyyyyyy","uuuuuuuuuuuuu","iiiiiiiiiii"};
    private boolean isRunning=true;
    private int number =0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                scrollText.next();
                number++;
                scrollText.setText(strings[number%strings.length]);
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        RollPagerView rollPager = (RollPagerView) view.findViewById(R.id.roll_pager);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.index_recycler);
        scrollText = (AutoVerticalScrollTextView) view.findViewById(R.id.autoscroll_text);
        scrollText.setText(strings[0]);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerAdapter = new RecyclerAdapter(this.getActivity());
        recyclerView.setAdapter(recyclerAdapter);

        rollPager.setAnimationDurtion(500);
        rollPager.setAdapter(new MyRollPagerAdapter(rollPager));

        new Thread(){
            @Override
            public void run() {
                while (isRunning){
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(199);
                }
            }
        }.start();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }
}
