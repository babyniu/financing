package com.github.financing.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.financing.R;
import com.github.financing.base.BaseActivity;
import com.github.financing.fragment.IndexFragment;
import com.github.financing.fragment.PersonalFragment;
import com.github.financing.fragment.ProductsFragment;
import com.github.financing.fragment.SortFragment;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener{

    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private static final String TAG_1 = "1";
    private static final String TAG_2 = "2";
    private static final String TAG_3 = "3";
    private static final String TAG_4 = "4";
    // 定义数组存放fragment
    private final Class fragmentArray[] = {IndexFragment.class, ProductsFragment.class, SortFragment.class, PersonalFragment.class};

    private int mImageViewArray[] = {R.drawable.tab_index_image,R.drawable.tab_product_image,R.drawable.tab_activity_image,R.drawable.tab_personal_image};
    private String mTextViewArray[] = {"首页","产品","活动","资产"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.homecontent);
        for (int i = 0;i < fragmentArray.length;i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(String.valueOf(i + 1)).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);

        }
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setOnTabChangedListener(this);
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextViewArray[index]);

        return view;
    }


    @Override
    public void onTabChanged(String tabId) {
        Log.i(TAG_1,"============"+tabId);

    }
}
