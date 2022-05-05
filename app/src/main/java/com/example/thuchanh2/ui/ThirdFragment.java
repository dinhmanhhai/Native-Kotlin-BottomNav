package com.example.thuchanh2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2.R;
import com.example.thuchanh2.data.db.SQLiteHelper;
import com.example.thuchanh2.data.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdFragment extends Fragment {

    Button btSearch, btThongKe, btSapSep;
    EditText etSearch;

    RecyclerView recyclerView;
    List<Item> itemList;
    List<Item> itemListSearch;
    List<Item> itemListSapXep;
    ItemAdapter adapter;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        btSearch = v.findViewById(R.id.btSearch);
        btThongKe = v.findViewById(R.id.bt_thong_ke);
        btSapSep = v.findViewById(R.id.bt_sapxep);
        etSearch = v.findViewById(R.id.et_search);
        recyclerView = v.findViewById(R.id.rv_search);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initListener();
    }

    private void initView() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
        itemList = sqLiteHelper.getAll();
        itemListSearch = new ArrayList<>();
        itemListSearch.addAll(itemList);
        itemListSapXep = new ArrayList<>();
        itemListSapXep.addAll(itemList);
        adapter = new ItemAdapter(getContext(), itemListSearch);
        adapter = new ItemAdapter(getContext(), itemListSapXep);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListSearch.clear();
                String searchText = etSearch.getText().toString();
                for (Item item : itemList){
                    if(item.getName().toLowerCase().contains(searchText.toLowerCase())
                    ||item.getDetail().toLowerCase().contains(searchText.toLowerCase() ))
                        itemListSearch.add(item);
                }

                adapter.notifyDataSetChanged();
            }
        });

        btThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map m = new HashMap<String, Integer>();

                for(Item item : itemList){
                    if(m.get(item.getStatus()) == null){
                        m.put(item.getStatus(), 0);
                    }

                    m.put(item.getStatus(), Integer.parseInt(String.valueOf(m.get(item.getStatus()))) + 1);
                    Log.d("Thong ke", item.getStatus() + " " +m.get(item.getStatus()) + "");
                }


            }
        });

        btSapSep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = itemListSapXep.size();
                System.out.println(n);
                Item temp =  itemListSapXep.get(0);
                Date date1 = new Date();
                Date date2 = new Date();
                for(int i=0; i < n; i++){
                    for(int j = 0; j < n-i-1; j++){
                        try {
                            date1=new SimpleDateFormat("dd/MM/yyyy").parse(itemListSapXep.get(j).getDate());
                            date2=new SimpleDateFormat("dd/MM/yyyy").parse(itemListSapXep.get(j+1).getDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(date2.compareTo(date1) == -1){
                            Collections.swap(itemListSapXep,j,j+1);

                        }

                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}