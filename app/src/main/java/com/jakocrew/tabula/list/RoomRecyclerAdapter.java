package com.jakocrew.tabula.list;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakocrew.tabula.R;
import com.jakocrew.tabula.common.ListViewDefine;
import com.jakocrew.tabula.data.RoomInfo;

import java.util.List;

/**
 * Created by zorosanji on 2017. 2. 28..
 */
public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.ViewHolder>{
    private static final int LAST_VISIBLE_POSISION = 0;

    private View headerView = null;
    private View footerView = null;

    private boolean useHeaderView = false;
    private boolean useFooterView = false;

    private Context mContext = null;
    private List<RoomInfo> mList = null;
    private int mLastPosition = LAST_VISIBLE_POSISION;
    private Interpolator mInterpolator = new LinearInterpolator();

    private long prevSaveTime = 0;
    private int mCurrentTabPosition = 0;

    public Handler handler_scroll;


    public RoomRecyclerAdapter(Context context, List<RoomInfo> objects){
        mContext = context;
        mList = objects;
    }

    public void setCurrentTabPosition(int pos) {
        mCurrentTabPosition = pos;
    }



    public void setScrollHandler(Handler handler) {
        this.handler_scroll = handler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ListViewDefine.TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else if (viewType == ListViewDefine.TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        } else {
            return onCreateBasicItemViewHolder(parent, viewType);
        }
    }

    public ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_room_layout, parent, false);

        return new ViewHolder(view);
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null) {
            return new ViewHolder(headerView);
        } else
            return null;
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        if (footerView != null) {
            return new ViewHolder(footerView);
        } else
            return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            onBindBaseItemView(holder, position - (useHeader() ? 1 : 0));

    }

    public void onBindBaseItemView(final ViewHolder holder, final int position) {
        holder.itemView.setTag(ListViewDefine.ID_POSITION, position);
        RoomInfo info = mList.get(position);
    }


    public void onBindHeaderView(ViewHolder holder, int position) {
        // don't need to bind for headerview
    }

    public void onBindFooterView(ViewHolder holder, int position) {
        // don't need to bind for footerview
    }

    @Override
    public int getItemCount() {

        int itemCount = getBasicItemCount();
        if (useHeader()) {
            itemCount += 1;
        }
        if (useFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    public int getBasicItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    public boolean useHeader() {
        return useHeaderView;
    }

    public boolean useFooter() {
        return useFooterView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && useHeader()) {
            return ListViewDefine.TYPE_HEADER;
        }

        if (position == getBasicItemCount() && useFooter()) {
            return ListViewDefine.TYPE_FOOTER;
        }
        int pos = position;
        if (useHeader()) {
            pos = pos - 1;
        }
        return getBasicItemType(position) + ListViewDefine.TYPE_ADAPTEE_OFFSET;
    }

    public int getBasicItemType(int position) {
        return ListViewDefine.TYPE_CONTENT;
    }

    public void addFooterView(View view) {
        footerView = view;
        useFooterView = true;
        notifyDataSetChanged();
    }

    public boolean removeFooterView(View view) {
        if( view != null && view.equals(headerView )){
            footerView = null;
            useFooterView = false;
            notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    public int getFooterViewsCount() {
        if (useFooterView)
            return 1;
        else
            return 0;
    }

    public void addHeaderView(View view) {
        headerView = view;
        useHeaderView = true;
        notifyDataSetChanged();
    }

    public boolean removeHeaderView(View view) {
        if( view != null && view.equals(headerView )){
            headerView = null;
            useHeaderView = false;
            notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    public int getHeaderViewsCount() {
        if (useHeaderView)
            return 1;
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_user;

        public ViewHolder(View rootView) {
            super(rootView);
            txt_user = (TextView) rootView.findViewById(R.id.txt_user);
        }
    }

}
