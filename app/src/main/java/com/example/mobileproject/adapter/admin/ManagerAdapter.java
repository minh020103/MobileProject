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

import com.example.mobileproject.R;
import com.example.mobileproject.model.ManagerModel;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.MyViewHolder> {

    private Activity activity;
    private List<ManagerModel> list;
    private int layoutID;

    public ManagerAdapter(Activity activity, List<ManagerModel> list, int layoutID) {
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
        ManagerModel data = list.get(position);
        if (data.getHinhNguoiQuanLi() != null){
            holder.hinhNguoiQuanLi.setImageDrawable(activity.getResources().getDrawable(R.drawable.app_management_selector, activity.getTheme()));

        }
        holder.tenNguoiQuanLi.setText(data.getTenNguoiQuanLi());
        holder.sdtNguoiQuanLi.setText(data.getSdtNguoiQuanLi());
        holder.tinhQuanLi.setText(data.getTinhQuanLi());
        //holder.soTienDaNhan.setText(data.getSoTienDaNhan());

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
        TextView tenNguoiQuanLi;
        ImageView hinhNguoiQuanLi;
        TextView sdtNguoiQuanLi;
        TextView tinhQuanLi;
        //TextView soTienDaNhan;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tenNguoiQuanLi = itemView.findViewById(R.id.tvTenQuanLi);
            hinhNguoiQuanLi = itemView.findViewById(R.id.imgHinhQuanLi);
            sdtNguoiQuanLi = itemView.findViewById(R.id.tvSdtQuanLi);
            tinhQuanLi = itemView.findViewById(R.id.tvTinhQuanLi);
            //soTienDaNhan = itemView.findViewById(R.id.tvSoTienDaNhanQuanLi);
        }
    }
}
