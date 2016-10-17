package com.github.financing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.github.financing.R;
import com.github.financing.adapter.DropDownAdapter;
import com.github.financing.adapter.RecyclerAdapter;
import com.github.financing.base.BaseFragment;
import com.github.financing.views.dropdownMenu.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************************
 * 作者：Administrator
 * 时间：2016/10/9
 * 描述：
 *******************************************/
public class ProductsFragment extends BaseFragment {
    private RecyclerAdapter recyclerAdapter;
    private DropDownAdapter typeDropDownAdapter;
    private DropDownAdapter orderDropDownAdapter;
    private String headers[] = {"类型", "期限"};
    private String typeArrays[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private String orderArrays[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private int constellationPosition = 0;
    private DropDownMenu mDropDownMenu;
    private List<View> popupViews = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prodcuts, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerAdapter = new RecyclerAdapter(this.getActivity());
        recyclerView.setAdapter(recyclerAdapter);

        mDropDownMenu = (DropDownMenu) view.findViewById(R.id.dropDownMenu);

        final View dropDownTypeView = inflater.inflate(R.layout.item_drop_down, null);
        final View dropDownOrderView = inflater.inflate(R.layout.item_drop_down, null);

        GridView typeGridView = (GridView)dropDownTypeView.findViewById(R.id.constellation);
        GridView orderGridView = (GridView)dropDownOrderView.findViewById(R.id.constellation);

        typeDropDownAdapter = new DropDownAdapter(this.getActivity(), Arrays.asList(typeArrays));
        orderDropDownAdapter = new DropDownAdapter(this.getActivity(), Arrays.asList(orderArrays));

        typeGridView.setAdapter(typeDropDownAdapter);
        orderGridView.setAdapter(orderDropDownAdapter);

        TextView typeOk = (TextView)dropDownTypeView.findViewById(R.id.ok);
        TextView orderOk = (TextView)dropDownOrderView.findViewById(R.id.ok);

        typeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[0] : typeArrays[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        orderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[1] : orderArrays[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        popupViews.add(dropDownTypeView);
        popupViews.add(dropDownOrderView);


        //init context view
        TextView contentView = new TextView(this.getActivity());
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,contentView);

        return view;
    }
}
