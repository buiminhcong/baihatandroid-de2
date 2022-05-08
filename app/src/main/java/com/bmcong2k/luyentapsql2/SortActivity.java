package com.bmcong2k.luyentapsql2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.bmcong2k.luyentapsql2.adapter.RecycleViewAdapter;
import com.bmcong2k.luyentapsql2.adapter.RecycleViewAdapterSort;
import com.bmcong2k.luyentapsql2.dal.SQliteHelper;
import com.bmcong2k.luyentapsql2.model.Sort;

import java.util.List;

public class SortActivity extends AppCompatActivity {

    private RecycleViewAdapterSort adapter;
    private RecyclerView recyclerView;
    private SQliteHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        recyclerView = findViewById(R.id.recycleviewSort);
        adapter = new RecycleViewAdapterSort();
        db = new SQliteHelper(this);

        Intent intent = getIntent();
        List<Sort> list = (List<Sort>) intent.getSerializableExtra("list");

        adapter.setList(list);

        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }


}