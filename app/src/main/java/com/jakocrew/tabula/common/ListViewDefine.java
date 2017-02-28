package com.jakocrew.tabula.common;

import android.graphics.Color;

/**
 * Created by zorosanji on 2017. 2. 28..
 */
public class ListViewDefine {
    public static final int LIST_BASIC_COLOR = Color.WHITE;
    public static final int LIST_SELECTED_COLOR = 0xfff3f3f3;

    public static final int TYPE_HEADER = Integer.MIN_VALUE;
    public static final int TYPE_FOOTER = Integer.MIN_VALUE + 1;
    public static final int TYPE_ADAPTEE_OFFSET = 2;
    public static final int ID_POSITION = -1;
    public static final int TYPE_CONTENT = Integer.MAX_VALUE - TYPE_ADAPTEE_OFFSET;
}
