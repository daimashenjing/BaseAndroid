package com.mvp.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.mvp.app.view.BaseView;

/**
 * Created by 史章华 on 2017/3/13.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    AppManager mAppManager = AppManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppManager.addActivity(this);
        ActionBar actionBar = getSupportActionBar();
        onInit(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭当前界面从AppManager移除
        mAppManager.delActivity(this);
    }

    /**
     * 根据Id 获取控件
     *
     * @param id
     * @return 控件
     */
    @Override
    public <T extends View> T getId(int id) {
        return (T) findViewById(id);
    }

    /**
     * 根据父容器获取控件
     * @param id
     * @param parent
     * @param <T>
     * @return 控件
     */
    public <T extends View> T getId(int id, View parent) {
        return (T) parent.findViewById(id);
    }
    /**
     * 返回
     */
    @Override
    public void goBack() {
        ActivityUtil.goBack(this);
    }

    /**
     * 可以设置动画返回
     *
     * @param inAnimId
     * @param outAnimId
     */
    @Override
    public void goBack(int inAnimId, int outAnimId) {
        ActivityUtil.goBack(this, inAnimId, outAnimId);
    }

    /**
     * @return 当前Activity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
