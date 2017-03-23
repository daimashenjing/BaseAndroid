package com.mvp.activity.model;

import com.mvp.activity.bean.AlbumBean;

import java.util.List;

/**
 * Created by HuaSao1024 on 2017/3/22.
 */

public interface IAlbumModel {

    void delItem(AlbumBean albumBean);

    void addItem(AlbumBean albumBean);

    AlbumBean getItem(int position);

    void setList(List<AlbumBean> albumList);

    List<AlbumBean> getList();
}
