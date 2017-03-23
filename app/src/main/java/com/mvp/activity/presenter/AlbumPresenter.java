package com.mvp.activity.presenter;

import com.mvp.activity.bean.AlbumBean;
import com.mvp.activity.model.IAlbumModel;
import com.mvp.activity.model.Impl.AlbumModel;
import com.mvp.activity.view.AlbumView;

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
        if (Datas != null) {
            items.addAll(Datas);
        }
        //添加null
        for (int i = Datas == null ? 0 : Datas.size(); i < maxNum; i++) {
            items.add(new AlbumBean());
        }
        albumModel.setList(items);
        albumView.loadData(albumModel.getList());
    }
}
