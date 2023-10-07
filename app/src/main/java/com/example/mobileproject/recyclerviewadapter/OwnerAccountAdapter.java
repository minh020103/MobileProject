package com.example.mobileproject.recyclerviewadapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.ChuTro;

import java.util.List;

public class OwnerAccountAdapter extends RecyclerView.Adapter<OwnerAccountAdapter.MyViewHolder> {

    private Activity activity;
    private int layoutID;
    private List<ChuTro> list;

    public OwnerAccountAdapter(Activity activity, int layoutID, List<ChuTro> list) {
        this.activity = activity;
        this.layoutID = layoutID;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        CardView view = (CardView) inflater.inflate(viewType, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChuTro chuTro = list.get(position);
        holder.tvTen.setText(chuTro.getTen());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhDaiDien;
        TextView tvTen;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhDaiDien = itemView.findViewById(R.id.cimgAnh);
            tvTen = itemView.findViewById(R.id.tvTen);
        }
    }
}
