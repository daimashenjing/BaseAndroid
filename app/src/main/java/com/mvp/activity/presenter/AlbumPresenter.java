package com.mvp.activity.presenter;

import android.view.View;

import com.mvp.activity.bean.AlbumBean;
import com.mvp.activity.model.IAlbumModel;
import com.mvp.activity.model.Impl.AlbumModel;
import com.mvp.activity.view.AlbumView;
import com.mvp.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuaSao1024 on 2017/3/22.
 */

public class AlbumPresenter {
    AlbumView albumView;
    IAlbumModel albumModel;

    private AlbumPresenter() {

    }

    public AlbumPresenter(AlbumView albumView) {
        this.albumView = albumView;
        this.albumModel = new AlbumModel();
    }

    public void LoadList(int maxNum, List<AlbumBean> Datas) {
        List<AlbumBean> items = new ArrayList<>();
        int position = 0;
        int len = Datas != null ? Datas.size() : 0;
        for (int i = 0; (i < len && i < maxNum); i++) {
            position++;
            items.add(Datas.get(i));
        }
        //添加null
        while (position < maxNum) {
            position++;
            items.add(new AlbumBean());
        }
        albumModel.setList(items);
        albumView.loadData(albumModel.getList());
    }

    public void onItemClick(View view, int position, boolean Photo) {
        L.showMeg("position : " + position + ",Photo : " + Photo);
    }
}
