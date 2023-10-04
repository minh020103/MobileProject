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
import com.example.mobileproject.model.HostModel;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.MyViewHolder> {

    private Activity activity;
    private List<HostModel> list;
    private int layoutID;

    OnClickItemListener onClickItemListene;

    public ManagerAdapter(Activity activity, List<HostModel> list, int layoutID) {
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
        HostModel data = list.get(position);
        Glide.with(activity.getLayoutInflater().getContext()).load(list.get(position).getHinhNguoiQuanLi()).into(holder.hinhNguoiQuanLi);
        holder.tenNguoiQuanLi.setText(data.getTenNguoiQuanLi());
        holder.sdtNguoiQuanLi.setText(data.getSdtNguoiQuanLi());
        holder.tinhQuanLi.setText(data.getTinhQuanLi());
        holder.soTienDaNhan.setText(String.valueOf(data.getSoTienDaNhan()));

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListene.onClickItem(position,v);
            }
        };



    }

    public OnClickItemListener getOnClickItemListene() {
        return onClickItemListene;
    }

    public void setOnClickItemListene(OnClickItemListener onClickItemListene) {
        this.onClickItemListene = onClickItemListene;
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
        TextView tenNguoiQuanLi;
        ImageView hinhNguoiQuanLi;
        TextView sdtNguoiQuanLi;
        TextView tinhQuanLi;
        TextView soTienDaNhan;

        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tenNguoiQuanLi = itemView.findViewById(R.id.tvTenQuanLi);
            hinhNguoiQuanLi = itemView.findViewById(R.id.imgHinhQuanLi);
            sdtNguoiQuanLi = itemView.findViewById(R.id.tvSdtQuanLi);
            tinhQuanLi = itemView.findViewById(R.id.tvTinhQuanLi);
            soTienDaNhan = itemView.findViewById(R.id.tvSoTienDaNhanQuanLi);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
