package com.mvp.activity.model.Impl;

import com.mvp.activity.bean.AlbumBean;
import com.mvp.activity.model.IAlbumModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuaSao1024 on 2017/3/22.
 */

public class AlbumModel implements IAlbumModel {
    List<AlbumBean> albumList = new ArrayList<>();


    @Override
    public void delItem(AlbumBean albumBean) {
        if (albumList != null) {
            albumList.remove(albumBean);
        }
    }

    @Override
    public void addItem(AlbumBean albumBean) {
        if (albumList != null) {
            albumList.add(albumBean);
        }
    }

    @Override
    public AlbumBean getItem(int position) {
        return albumList != null ? albumList.size() > position ? albumList.get(position) : null : null;
    }

    @Override
    public void setList(List<AlbumBean> albumList) {
        this.albumList = albumList;
    }

    @Override
    public List<AlbumBean> getList() {
        return albumList;
    }
}
