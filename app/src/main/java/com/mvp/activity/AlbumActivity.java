package com.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.mvp.app.BaseActivity;
import com.mvp.app.R;
import com.mvp.model.PhotoItem;
import com.mvp.utils.L;
import com.mvp.view.AlbumView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuaSao1024 on 2017/3/16.
 */

public class AlbumActivity extends BaseActivity implements View.OnClickListener, AlbumView.OnItemClickListener{
    private AlbumView mAlbumView;
    private RelativeLayout msetUserInfoLayout;
    @Override
    public void onInit(Bundle savedInstanceState) {
        setContentView(R.layout.activity_album);
        mAlbumView = getId(R.id.imageListView);
        mAlbumView.setRootView((GridLayout) getId(R.id.Rootlayout));
        mAlbumView.setImages(moarItems(6, getImageDate()));
        msetUserInfoLayout = getId(R.id.setUserInfoLayout);
        msetUserInfoLayout.setOnClickListener(this);
        mAlbumView.setOnItemClickListener(this);
    }
    public List<PhotoItem> moarItems(int qty, List<PhotoItem> Datas) {
        List<PhotoItem> items = new ArrayList<>();
        if(Datas!=null){
            items.addAll(Datas);
        }
        //添加null
        for (int i = Datas == null ? 0 : Datas.size(); i < qty; i++) {
            items.add(new PhotoItem());
        }
        return items;
    }
    private List<PhotoItem> getImageDate() {
        // TODO Auto-generated method stub
        List<PhotoItem> mDataList = new ArrayList<>();
        PhotoItem item = new PhotoItem();
        item.hyperlink = "drawable://" + R.mipmap.head6;
        item.id = 1;
        item.sort = 1;
        mDataList.add(item);

        item = new PhotoItem();
        item.hyperlink = "drawable://" + R.mipmap.head7;
        item.id = 2;
        item.sort = 2;
        mDataList.add(item);

        item = new PhotoItem();
        item.hyperlink = "drawable://" + R.mipmap.head8;
        item.id = 3;
        item.sort = 3;
        mDataList.add(item);

        item = new PhotoItem();
        item.hyperlink = "drawable://" + R.mipmap.head9;
        item.id = 4;
        item.sort = 4;
        mDataList.add(item);

//		item = new PhotoItem();
//		item.hyperlink = "drawable://" + R.mipmap.head10;
//		item.id = 5;
//		item.sort = 5;
//		mDataList.add(item);
//
//		item = new PhotoItem();
//		item.hyperlink = "drawable://" + R.mipmap.head1;
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
}
