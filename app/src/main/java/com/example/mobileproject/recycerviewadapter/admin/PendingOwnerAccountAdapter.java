package com.example.mobileproject.recycerviewadapter.admin;

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

public class PendingOwnerAccountAdapter extends RecyclerView.Adapter<PendingOwnerAccountAdapter.MyViewHolder> {

    private Activity activity;
    private int layoutID;
    private List<ChuTro> list;
    private OnClickItemListener onClickItemListener;

    public PendingOwnerAccountAdapter(Activity activity, int layoutID, List<ChuTro> list) {
        this.activity = activity;
        this.layoutID = layoutID;
        this.list = list;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
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
        holder.tvTen.setText(chuTro.getTenNguoiDung());
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClick(position, v);
            }
        };
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public interface OnClickItemListener {
        void onClick(int position, View view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgHinhDaiDien;
        TextView tvTen;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhDaiDien = itemView.findViewById(R.id.cimgAnh);
            tvTen = itemView.findViewById(R.id.tvTen);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
