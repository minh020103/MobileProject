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

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.YeuCauXacThuc;

import java.util.List;

public class PendingOwnerAccountAdapter extends RecyclerView.Adapter<PendingOwnerAccountAdapter.MyViewHolder> {

    private Activity activity;
    private int layoutID;
    private List<YeuCauXacThuc> list;
    private OnClickItemListener onClickItemListener;

    public PendingOwnerAccountAdapter(Activity activity, int layoutID, List<YeuCauXacThuc> list) {
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
        YeuCauXacThuc yeuCauXacThuc = list.get(position);
        if (list.get(position).getChuTro() != null) {
            Glide.with(activity.getLayoutInflater().getContext()).load(Const.DOMAIN + list.get(position).getChuTro().getHinh()).into(holder.imgHinhDaiDien);

            holder.tvTen.setText(yeuCauXacThuc.getChuTro().getTen());
        }
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
