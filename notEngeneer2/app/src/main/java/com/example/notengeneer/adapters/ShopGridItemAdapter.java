package com.example.notengeneer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notengeneer.R;
import com.example.notengeneer.models.ShopItem;

import java.util.List;

public class ShopGridItemAdapter extends BaseAdapter {

    private List<ShopItem> list;
    Context context;

    public ShopGridItemAdapter(Context context, List<ShopItem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = (LayoutInflater.from(context)).inflate(R.layout.shop_item, null);
        ImageView photo = view.findViewById(R.id.shopGridItemIcon);

        ((TextView) view.findViewById(R.id.shopGridItemTitle)).setText(list.get(i).description);
        ((TextView) view.findViewById(R.id.shopGridItemPrice)).setText("€ " + list.get(i).price);

        return view;
    }
}
