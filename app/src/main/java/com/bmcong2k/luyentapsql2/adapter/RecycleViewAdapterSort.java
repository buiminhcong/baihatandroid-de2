package com.bmcong2k.luyentapsql2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bmcong2k.luyentapsql2.R;
import com.bmcong2k.luyentapsql2.model.BaiHat;
import com.bmcong2k.luyentapsql2.model.Sort;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterSort extends RecyclerView.Adapter<RecycleViewAdapterSort.HomeViewHoler> {

    private List<Sort> list;

    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public RecycleViewAdapterSort() {
        list = new ArrayList<>();
    }

    public void setList(List<Sort> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Sort getItem(int po){
        return list.get(po);
    }

    @NonNull
    @Override
    public HomeViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_item, parent,
                false);
        return new HomeViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHoler holder, int position) {

        Sort item = getItem(position);
        holder.tl.setText(item.gettL());
        holder.sl.setText(String.valueOf(item.getDem()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tl, sl;

        public HomeViewHoler(@NonNull View itemView) {
            super(itemView);
            tl= itemView.findViewById(R.id.txtTL);
            sl = itemView.findViewById(R.id.txtSL);
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
