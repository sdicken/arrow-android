package com.arrowfoodcouriers.arrowfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.DrawerListObject;
import com.arrowfoodcouriers.arrowfood.DrawerValues;
import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;

/**
 * Created by Ryan on 2/6/14.
 */
public class DrawerListAdapter extends BaseAdapter {

    private static final int DRAWER_LIST_VIEW_COUNT = 2;
    private static final int DRAWER_LIST_SECTION = 1;
    private static final int DRAWER_LIST_ITEM = 0;

    @Override
    public int getCount() {
        return DrawerValues.DRAWER_VALUES.length;
    }

    @Override
    public Object getItem(int position) {
        return DrawerValues.DRAWER_VALUES[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return DRAWER_LIST_VIEW_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (DrawerValues.DRAWER_VALUES[position] instanceof  String) ?
                DRAWER_LIST_SECTION : DRAWER_LIST_ITEM;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != DRAWER_LIST_SECTION;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final int type = getItemViewType(position);

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    type == DRAWER_LIST_SECTION ? R.layout.drawer_list_header : R.layout.drawer_list_item,
                    parent, false);
        }

        if (type == DRAWER_LIST_SECTION) {
            ((TextView)view).setText((String) getItem(position));
        } else {
            ((TextView) view).setText(((DrawerListObject) getItem(position)).title);
        }

        return view;
    }


}
