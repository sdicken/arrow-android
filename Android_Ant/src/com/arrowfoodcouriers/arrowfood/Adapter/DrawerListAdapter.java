package com.arrowfoodcouriers.arrowfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.DrawerListObject;
import com.arrowfoodcouriers.arrowfood.DrawerValues;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.R;

/**
 * Created by Ryan on 2/6/14.
 */
public class DrawerListAdapter extends BaseAdapter {

    private static final int DRAWER_LIST_VIEW_COUNT = 2;
    private static final int DRAWER_LIST_SECTION = 1;
    private static final int DRAWER_LIST_ITEM = 0;

    private ISession _session;
    private DrawerValues mDrawerValues;

    public DrawerListAdapter(ISession session) {
        this._session = session;
        mDrawerValues = new DrawerValues(_session);
    }
    
    public int getCount() {
        return mDrawerValues.getDrawerValues().length;
    }

    public Object getItem(int position) {
        return mDrawerValues.getDrawerValues()[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return DRAWER_LIST_VIEW_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (mDrawerValues.getDrawerValues()[position] instanceof  String) ?
                DRAWER_LIST_SECTION : DRAWER_LIST_ITEM;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != DRAWER_LIST_SECTION;
    }
    
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
            TextView textView = (TextView)view.findViewById(R.id.list_item_text);
            ImageView imageView = (ImageView)view.findViewById(R.id.list_item_icon);
            DrawerListObject drawerListObject = (DrawerListObject)getItem(position);
            textView.setText(drawerListObject.title);
            imageView.setImageResource(drawerListObject.image);
        }

        return view;
    }


}
