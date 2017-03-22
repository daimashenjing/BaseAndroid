package com.mvp.app.view;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 史章华 on 2017/3/12.
 */

public interface BaseView {
    <T extends View> T getId(int id);

    <T extends View> T getId(int id, View parent);

    void goBack();

    void goBack(int inAnimId, int outAnimId);

    Activity getActivity();

    void onInit(Bundle savedInstanceState);
}
