 package com.bmcong2k.luyentapsql2.fragment;


 import android.content.Intent;
 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.fragment.app.Fragment;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.bmcong2k.luyentapsql2.R;
 import com.bmcong2k.luyentapsql2.UpdateDeleteActivity;
 import com.bmcong2k.luyentapsql2.adapter.RecycleViewAdapter;
 import com.bmcong2k.luyentapsql2.dal.SQliteHelper;
 import com.bmcong2k.luyentapsql2.model.BaiHat;

 import java.util.ArrayList;
 import java.util.List;

 public class FragmentDanhSach extends Fragment   implements RecycleViewAdapter.ItemListener{

     private RecycleViewAdapter adapter;
     private RecyclerView recyclerView;
     private SQliteHelper db;

     @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_danhsach,container,  false);
     }

     @Override
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);

         recyclerView = view.findViewById(R.id.recycleView);
         adapter = new RecycleViewAdapter();

         db = new SQliteHelper(getContext());
         // tao db gia
//         BaiHat b = new BaiHat(1, "Chắc ai đó sẽ về", "Sơn Tùng", "Tuổi trẻ", "Trẻ");
//         BaiHat b2 = new BaiHat(2, "Chắc ai đó sẽ về", "Sơn Tùng", "Tuổi trẻ", "Trẻ");
         List<BaiHat> list = db.getAll();
//         list.add(b);
//         list.add(b2);
         adapter.setList(list);
         LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
         recyclerView.setLayoutManager(manager);
         recyclerView.setAdapter(adapter);

         adapter.setItemListener(this);
     }

     @Override
     public void onItemClick(View view, int position) {
         BaiHat baiHat = adapter.getItem(position);
         Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
         intent.putExtra("baiHat", baiHat);
         startActivity(intent);
     }

     // lam tuoi lai database
     @Override
     public void onResume() {
         super.onResume();
         List<BaiHat> list = db.getAll();
         adapter.setList(list);
     }


 }
