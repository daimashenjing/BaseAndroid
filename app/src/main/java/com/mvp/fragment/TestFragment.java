package com.mvp.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mvp.activity.AlbumActivity;
import com.mvp.app.ActivityUtil;
import com.mvp.app.BaseFragment;
import com.mvp.app.R;

/**
 * Created by HuaSao1024 on 2017/3/22.
 */

public class TestFragment extends BaseFragment implements View.OnClickListener{
    Button TestOne, TestTwo;

    @Override
    public void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);
        initUI();
        initEvent();
    }

    private void initUI() {
        TestOne = getId(R.id.TestOne);
        TestTwo = getId(R.id.TestTwo);
    }

    private void initEvent() {
        TestOne.setOnClickListener(this);
        TestTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TestOne:
                ActivityUtil.next(getActivity(), AlbumActivity.class);
                break;
            case R.id.TestTwo:
                ActivityUtil.next(getActivity(), AlbumActivity.class);
                break;
            default:
                break;
        }
    }
}
