package com.example.mobileproject.adapter.admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.GoiDichVu;

import org.w3c.dom.Text;

import java.util.List;

public class GoiDichVuAdapter extends RecyclerView.Adapter<GoiDichVuAdapter.MyViewHolder>{

    private Activity activity;
    private List<GoiDichVu> list;
    private int layoutID;

    OnClickItemListener onClickItemListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        CardView view = (CardView) inflater.inflate(viewType, parent,false);
        return new MyViewHolder(view);
    }

    public GoiDichVuAdapter(Activity activity, List<GoiDichVu> list, int layoutID) {
        this.activity = activity;
        this.list = list;
        this.layoutID = layoutID;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GoiDichVu data = list.get(position);
        holder.tvIdGoiDV.setText(String.valueOf(data.getId()));
        holder.tvThoiHanGoiDV.setText(String.valueOf(data.getThoiHan()));
        holder.tvSoLuongGoiDV.setText(String.valueOf(data.getSoLuongPhongToiDa()));
        holder.tvGiaGoi.setText(String.valueOf(data.getGia()));
        if (data.getTrangThai() == 1)
        {
            holder.tvTrangThai.setText("Đã khóa");
            holder.tvTrangThai.setTextColor(0xFFFF0000);
        }
        else
        {
            holder.tvTrangThai.setText("Đang hoạt động");
            holder.tvTrangThai.setTextColor(0xFF00FF00);
        }
        //holder.tvTrangThai.setText(String.valueOf(data.getTrangThai()));
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItem(position, v);
            }
        };

    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnClickItemListener{
        void onClickItem(int position, View v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvIdGoiDV;
        TextView tvThoiHanGoiDV;
        TextView tvSoLuongGoiDV;
        TextView tvGiaGoi;
        TextView tvTrangThai;

        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdGoiDV = itemView.findViewById(R.id.tvIdGoiDV);
            tvThoiHanGoiDV = itemView.findViewById(R.id.tvThoiHanGoiDV);
            tvSoLuongGoiDV = itemView.findViewById(R.id.tvSoLuongPhongGoiDV);
            tvGiaGoi = itemView.findViewById(R.id.tvGiaGoiDV);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThaiGoiDV);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
