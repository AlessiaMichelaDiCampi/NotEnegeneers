package com.example.notengeneer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.notengeneer.App;
import com.example.notengeneer.R;
import com.example.notengeneer.activities.ShopItemActivity;
import com.example.notengeneer.adapters.ShopGridItemAdapter;
import com.example.notengeneer.models.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    View view;
    GridView offersGrid;

    App app = App.getInstance();

    ArrayList<ShopItem> test;

    public ShopFragment() {
        test = new ArrayList<>();
        test.add(new ShopItem(0, "Mona","Tanta mona", null, null, 3.50f,50));
        test.add(new ShopItem(1, "Mona1","Tanta mona", null, null, 3.50f,50));
        test.add(new ShopItem(2, "Mona2","Tanta mona", null, null, 3.50f,50));
        test.add(new ShopItem(3, "Mona3","Tanta mona", null, null, 3.50f,50));
        test.add(new ShopItem(4, "Mona4","Tanta mona", null, null, 3.50f,50));
        test.add(new ShopItem(5, "Mona5","Tanta mona", null, null, 3.50f,50));
        test.add(new ShopItem(6, "Mona6","Tanta mona", null, null, 3.50f,50));
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);

        offersGrid = (GridView) view.findViewById(R.id.shopGrid);
        offersGrid.setAdapter(new ShopGridItemAdapter(getContext(), test));

        offersGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(getActivity(), ShopItemActivity.class);
                intent.putExtra("selected", id);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}