package com.jakocrew.tabula.common;

import android.view.View;

import java.util.List;

/**
 * Created by zorosanji on 2017. 2. 28..
 */
public interface ListViewInterface {
    int getLastVisiblePosition();
    int getFirstVisiblePosition();

    void addFooterView(View view);
    boolean removeFooterView(View view);
    int getFooterViewsCount();

    void addHeaderView(View view);
    boolean removeHeaderView(View view);
    int getHeaderViewsCount();

    List getListData();
}
