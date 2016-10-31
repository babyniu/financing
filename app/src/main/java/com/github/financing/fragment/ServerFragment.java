package com.github.financing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.financing.R;
import com.github.financing.base.BaseFragment;
import com.github.financing.ui.TestActivity;

/********************************************
 * 作者：Administrator
 * 时间：2016/10/9
 * 描述：
 *******************************************/
public class ServerFragment extends BaseFragment {
    private View view;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_activity, null);
            button = (Button)view.findViewById(R.id.activity_register);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),TestActivity.class);
                    getActivity().startActivity(intent);

                }
            });
        }
//        Button btn = (Button) view.findViewById(R.id.order_btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),TestActivity.class);
//                getActivity().startActivity(intent);
//
//            }
//        });
        ViewGroup parent = (ViewGroup)view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        return view;
    }

}
