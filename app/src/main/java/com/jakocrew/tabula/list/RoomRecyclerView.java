package com.jakocrew.tabula.list;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.jakocrew.tabula.common.ListViewInterface;
import com.jakocrew.tabula.data.RoomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zorosanji on 2017. 2. 28..
 */
public class RoomRecyclerView extends RecyclerView implements ListViewInterface{
    private RoomRecyclerAdapter mListAdapter;
    private ArrayList<RoomInfo> mArrList;

    private GridLayoutManager layoutManager = null;

    private Context mContext = null;

    public int mCurrentTabPosition = 0;

    private Handler handler_scroll = new Handler(){
        public void handleMessage(Message msg) {
            scrollToPosition(msg.what);
            smoothScrollBy(0, msg.arg1);
        }
    };


    public RoomRecyclerView(Context context) {
        super(context);
        mContext = context;
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setVerticalFadingEdgeEnabled(false);

        /**
         * 그리드 레이아웃 매니저를 통해 3 X ~ 형식의 리스트로 노출한다.
         */
        layoutManager = new GridLayoutManager(context, 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        setLayoutManager(layoutManager);
    }
    public RoomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setVerticalFadingEdgeEnabled(false);

        /**
         * 그리드 레이아웃 매니저를 통해 3 X ~ 형식의 리스트로 노출한다.
         */
        layoutManager = new GridLayoutManager(context, 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        setLayoutManager(layoutManager);
    }

    public void setCurrentTabPosition(int pos)
    {
        mCurrentTabPosition = pos;
    }

    public void setListData(ArrayList<RoomInfo> arrayData) {
        if (arrayData != null) {
            mArrList = new ArrayList<RoomInfo>();
            for (int i = 0; i < arrayData.size(); i++) {
                mArrList.add(arrayData.get(i));
            }
            mListAdapter = new RoomRecyclerAdapter(mContext, mArrList);
            mListAdapter.setCurrentTabPosition(mCurrentTabPosition);
            mListAdapter.setScrollHandler(handler_scroll);
            setAdapter(mListAdapter);
        }
    }

    @Override
    public int getLastVisiblePosition() {
        if( layoutManager != null )
            return layoutManager.findLastVisibleItemPosition();
        else
            return 0;
    }

    @Override
    public int getFirstVisiblePosition() {
        if( layoutManager != null )
            return layoutManager.findFirstVisibleItemPosition();
        else
            return 0;
    }

    @Override
    public void addFooterView(View view) {
        if( mListAdapter != null )
            mListAdapter.addFooterView(view);
    }

    @Override
    public boolean removeFooterView(View view) {
        if( mListAdapter != null )
            return mListAdapter.removeFooterView(view);
        else
            return false;
    }

    @Override
    public int getFooterViewsCount() {
        if( mListAdapter != null )
            return mListAdapter.getFooterViewsCount();
        else
            return 0;
    }

    @Override
    public void addHeaderView(View view) {
        if( mListAdapter != null )
            mListAdapter.addHeaderView(view);
    }

    @Override
    public boolean removeHeaderView(View view) {
        if( mListAdapter != null )
            return mListAdapter.removeHeaderView(view);
        else
            return false;
    }

    @Override
    public int getHeaderViewsCount() {
        if( mListAdapter != null )
            return mListAdapter.getHeaderViewsCount();
        else
            return 0;
    }

    @Override
    public List getListData() {
        return mArrList;
    }
}
