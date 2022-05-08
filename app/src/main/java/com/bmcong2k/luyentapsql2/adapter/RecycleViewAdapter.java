package com.bmcong2k.luyentapsql2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bmcong2k.luyentapsql2.R;
import com.bmcong2k.luyentapsql2.model.BaiHat;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHoler> {

    private List<BaiHat> list;
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<BaiHat> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public BaiHat getItem(int po){
        return list.get(po);
    }

    @NonNull
    @Override
    public HomeViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,
                false);
        return new HomeViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHoler holder, int position) {

        BaiHat item = getItem(position);
        holder.tenBaiHat.setText(item.getTenBH());
        holder.tenCaSi.setText(item.getTenCasi());
        holder.album.setText(item.getAlbum());
        holder.theLoai.setText(item.getTheLoai());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tenBaiHat, tenCaSi, album, theLoai;

        public HomeViewHoler(@NonNull View itemView) {
            super(itemView);
            tenBaiHat= itemView.findViewById(R.id.tvTenBH);
            tenCaSi= itemView.findViewById(R.id.tvTenCaSi);
            album= itemView.findViewById(R.id.tvAlbum);
            theLoai= itemView.findViewById(R.id.tvTheLoai);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener != null){
                itemListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    // OVeright click item

    public interface ItemListener{
        void onItemClick(View view, int position);
    }

}
