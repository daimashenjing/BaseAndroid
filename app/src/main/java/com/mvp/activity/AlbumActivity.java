package com.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.mvp.activity.bean.AlbumBean;
import com.mvp.activity.presenter.AlbumPresenter;
import com.mvp.activity.view.AlbumView;
import com.mvp.app.BaseActivity;
import com.mvp.app.R;
import com.mvp.utils.L;
import com.mvp.widget.AlbumListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuaSao1024 on 2017/3/16.
 */

public class AlbumActivity extends BaseActivity implements View.OnClickListener, AlbumListView.OnItemClickListener,AlbumView {
    private AlbumListView mAlbumListView;
    private RelativeLayout msetUserInfoLayout;
    AlbumPresenter presenter;
    @Override
    public void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_album);
        mAlbumListView = getId(R.id.imageListView);
        mAlbumListView.setRootView((GridLayout) getId(R.id.Rootlayout));

        msetUserInfoLayout = getId(R.id.setUserInfoLayout);
        msetUserInfoLayout.setOnClickListener(this);
        mAlbumListView.setOnItemClickListener(this);

        presenter = new AlbumPresenter(this);
        presenter.LoadList(6, getImageDate());
    }

    private List<AlbumBean> getImageDate() {
        // TODO Auto-generated method stub
        List<AlbumBean> mDataList = new ArrayList<>();
        AlbumBean item = new AlbumBean();
        item.imgUrl = "drawable://" + R.mipmap.head6;
        item.id = 1;
        mDataList.add(item);

        item = new AlbumBean();
        item.imgUrl = "drawable://" + R.mipmap.head7;
        item.id = 2;
        mDataList.add(item);

        item = new AlbumBean();
        item.imgUrl = "drawable://" + R.mipmap.head8;
        item.id = 3;
        mDataList.add(item);

        item = new AlbumBean();
        item.imgUrl = "drawable://" + R.mipmap.head9;
        item.id = 4;
        mDataList.add(item);

//		item = new AlbumBean();
//		item.imgUrl = "drawable://" + R.mipmap.head10;
//		item.id = 5;
//		item.sort = 5;
//		mDataList.add(item);
//
//		item = new AlbumBean();
//		item.imgUrl = "drawable://" + R.mipmap.head1;
//		item.id = 6;
//		item.sort = 6;
//		mDataList.add(item);

        return mDataList;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onItemClick(View view, int position, boolean Photo) {
        // TODO Auto-generated method stub
        // Photo 照片还是空格子
        L.showMeg("position : "+position + ",Photo : " + Photo);
    }

    @Override
    public void loadData(List<AlbumBean> Datas) {
        mAlbumListView.setImages(Datas);
    }
}
