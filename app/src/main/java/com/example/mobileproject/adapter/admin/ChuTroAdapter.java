package com.example.mobileproject.adapter.admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.ChuTro;

import java.util.List;

public class ChuTroAdapter extends RecyclerView.Adapter<ChuTroAdapter.MyViewHolder> {

    private Activity activity;
    private List<ChuTro> list;
    private int layoutID;

    OnClickItemListener onClickItemListener;

    public ChuTroAdapter(Activity activity, List<ChuTro> list, int layoutID) {
        this.activity = activity;
        this.list = list;
        this.layoutID = layoutID;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        CardView view = (CardView) inflater.inflate(viewType, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChuTro data = list.get(position);
        Glide.with(activity.getLayoutInflater().getContext()).load(list.get(position).getHinh()).into(holder.imgChuTro);
        holder.tvTenChuTro.setText(data.getTen());
        holder.tvSDTChuTro.setText(data.getSoDienThoai());
        if (data.getXacThuc() == 1)
        {
            holder.tvTrangThaiChuTro.setText("Đã xác thực");
            holder.tvTrangThaiChuTro.setTextColor(0xFF00FF00);
        }
        else
        {
            holder.tvTrangThaiChuTro.setText("Chưa xác thực");
            holder.tvTrangThaiChuTro.setTextColor(0xFFFF0000);
        }
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItem(position, v);
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

    public interface OnClickItemListener{
        void onClickItem(int position, View v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTenChuTro;
        ImageView imgChuTro;
        TextView tvSDTChuTro;
        TextView tvTrangThaiChuTro;

        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenChuTro = itemView.findViewById(R.id.tvTenChuTro);
            imgChuTro = itemView.findViewById(R.id.imgChuTro);
            tvSDTChuTro = itemView.findViewById(R.id.tvSDTChuTro);
            tvTrangThaiChuTro = itemView.findViewById(R.id.tvTrangThaiChuTro);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
