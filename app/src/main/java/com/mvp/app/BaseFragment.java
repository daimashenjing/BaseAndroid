package com.mvp.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.app.view.BaseView;

/**
 * Created by 史章华 on 2017/3/13.
 * 让Fragment使用方法和Activity相似
 * 方便Fragment与Activity以后的互相转换
 */
public abstract class BaseFragment extends FixedOnActivityResultBugFragment implements BaseView {
    private View mView;
    /**
     * Fragment当前状态是否可见
     */
    private LayoutInflater inflater;
    private ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        onInit(savedInstanceState);
        if (mView == null) {
            throw new NullPointerException("view is null");
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);// 先移除
        }
        return mView;
    }

    public void setContentView(int layoutResID) {
        setContentView(inflater.inflate(layoutResID, container, false));
    }

    public void setContentView(View view) {
        mView = view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mView = null;
        container = null;
        inflater = null;
    }

    /**
     * 根据Id 获取控件
     *
     * @param id
     * @return 控件
     */
    @Override
    public <T extends View> T getId(int id) {
        return (T) mView.findViewById(id);
    }

    /**
     * 根据父容器获取控件
     *
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
        ActivityUtil.goBack(getActivity());
    }

    /**
     * 可以设置动画返回
     *
     * @param inAnimId
     * @param outAnimId
     */
    @Override
    public void goBack(int inAnimId, int outAnimId) {
        ActivityUtil.goBack(getActivity(), inAnimId, outAnimId);
    }

}
