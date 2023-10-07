package com.example.mobileproject.adapter.admin;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.model.ServiceModel;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    private Activity activity;
    private List<ServiceModel> list;
    private int layoutID;


    private OnClickItemListener onClickItemListener;


    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public ServiceAdapter(Activity activity, List<ServiceModel> list, int layoutID) {
        this.activity = activity;
        this.list = list;
        this.layoutID = layoutID;
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
        ServiceModel data = list.get(position);
        holder.tvServiceId.setText(String.valueOf(data.getId()));
        holder.tvServiceThoiHan.setText(String.valueOf(data.getThoiHan()));
        holder.tvServiceSoLuongPhong.setText(String.valueOf(data.getSoLuongPhong()));
        holder.tvServiceGia.setText(String.valueOf(data.getGiaGoi()));
        if (data.getTrangThai() == 1)
        {
            holder.tvServiceTrangThai.setText("Da Khoa");
            holder.tvServiceTrangThai.setBackgroundColor(0xFFFF0000);
        }
        else
        {
            holder.tvServiceTrangThai.setText("Dang Hoat Dong");
            holder.tvServiceTrangThai.setBackgroundColor(0xFF00FF00);
        }
        //holder.tvServiceTrangThai.setText(data.getTrangThai());

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position, v);
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
        void onClickItemListener(int position, View view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvServiceId;
        TextView tvServiceThoiHan;
        TextView tvServiceSoLuongPhong;
        TextView tvServiceGia;
        TextView tvServiceTrangThai;

        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceId = itemView.findViewById(R.id.tvServiceId);
            tvServiceThoiHan = itemView.findViewById(R.id.tvServiceThoiHan);
            tvServiceSoLuongPhong = itemView.findViewById(R.id.tvServiceSoLuongPhong);
            tvServiceGia = itemView.findViewById(R.id.tvServiceGia);
            tvServiceTrangThai = itemView.findViewById(R.id.tvServiceTrangThai);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
