package com.bmcong2k.luyentapsql2.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

;
import com.bmcong2k.luyentapsql2.MainActivity;
import com.bmcong2k.luyentapsql2.R;
import com.bmcong2k.luyentapsql2.SortActivity;
import com.bmcong2k.luyentapsql2.adapter.RecycleViewAdapter;
import com.bmcong2k.luyentapsql2.dal.SQliteHelper;
import com.bmcong2k.luyentapsql2.model.BaiHat;
import com.bmcong2k.luyentapsql2.model.Sort;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener{



    private RecyclerView recyclerView;
    private Button btnSearch;
    private SearchView searchView;
    private EditText eFrom, eTo;
    private Spinner spAlbum;
    private RecycleViewAdapter adapter;
    private SQliteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,  false);
    }

    @Override
    public void onClick(View v) {
        //serach tu dau den dau
//        if(v == btnSearch){
//            String from = eFrom.getText().toString();
//            String to = eTo.getText().toString();
//            if(!from.isEmpty() && !to.isEmpty()){
//                List<Book> list = db.searchByPriceFromTo(from, to);
//                adapter.setList(list);
//            }
//        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        adapter = new RecycleViewAdapter();
        db = new SQliteHelper(getContext());
        List<BaiHat> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                List<Item> list1 = db.searchByTitle(newText);
//                tvTong.setText("Tong Tien: "+tong(list1) + "K");
//                adapter.setList(list1);
//                return true;
//            }
//        });

//        eFrom.setOnClickListener(this);
//        eTo.setOnClickListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Sort> list1 = new ArrayList<>();
                list1 = db.sortByTheLoai();

                Intent intent = new Intent(getContext(), SortActivity.class);
                intent.putExtra("list",(Serializable) list1);
                startActivity(intent);

            }
        });
        spAlbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String album = spAlbum.getItemAtPosition(position).toString();
                List<BaiHat> list;
                list = db.searchByAlbum(album);
                adapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycleView);
        btnSearch = view.findViewById(R.id.btnSearch);
        searchView = view.findViewById(R.id.search);
        eFrom = view.findViewById(R.id.eFrom);
        eTo = view.findViewById(R.id.eTo);
        spAlbum = view.findViewById(R.id.spAblum);

//        String[] arr = getResources().getStringArray(R.array.category);
//        String[] arr1 = new String[arr.length+1];
//        arr1[0] = "All";
//        for (int i=0; i<arr.length; i++){
//            arr1[i+1] = arr[i];
//        }
        spAlbum.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spriner,
                getResources().getStringArray(R.array.album)));



    }

//    private int tong(List<Item> list){
//        int t = 0;
//        for(Item i : list){
//            t+=Integer.parseInt(i.getPrice());
//        }
//        return t;
//    }


}
