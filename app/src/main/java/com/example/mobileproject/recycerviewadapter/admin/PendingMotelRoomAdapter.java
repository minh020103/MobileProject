package com.example.mobileproject.recycerviewadapter.admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.PhongTro;
import com.example.mobileproject.datamodel.YeuCauXoaPhong;

import java.security.PublicKey;
import java.util.List;

public class PendingMotelRoomAdapter extends RecyclerView.Adapter<PendingMotelRoomAdapter.MyViewHolder> {

    private Activity activity;
    private List<YeuCauXoaPhong> list;
    private int layoutID;
    private OnClick onClick;

    public PendingMotelRoomAdapter(Activity activity, List<YeuCauXoaPhong> list, int layoutID) {
        this.activity = activity;
        this.list = list;
        this.layoutID = layoutID;
    }

    public interface OnClick{
        void onClickItemListener(int position, View v);
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
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
        YeuCauXoaPhong yeuCauXoaPhong = list.get(position);
        if (list.get(position).getChuTro() != null) {
            holder.tvTen.setText(list.get(position).getChuTro().getTen());
        }
        else {
            holder.tvTen.setText("ChuTro: N/A");
        }
        if (list.get(position).getPhongTro() != null) {
            holder.tvIdPhong.setText("#" + list.get(position).getPhongTro().getId());
        }
        else {
            holder.tvIdPhong.setText("Phòng: N/A");
        }

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickItemListener(position, v);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTen;
        TextView tvIdPhong;
        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvIdPhong = itemView.findViewById(R.id.tvIdPhong);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
