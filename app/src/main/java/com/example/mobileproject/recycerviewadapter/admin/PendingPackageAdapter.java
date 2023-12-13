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
import com.example.mobileproject.datamodel.YeuCauDangKyGoi;

import java.util.List;

public class PendingPackageAdapter extends RecyclerView.Adapter<PendingPackageAdapter.MyViewHolder> {

    private Activity activity;
    private List<YeuCauDangKyGoi> list;
    private int layoutID;
    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public PendingPackageAdapter(Activity activity, List<YeuCauDangKyGoi> list, int layoutID) {
        this.activity = activity;
        this.list = list;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        CardView view = (CardView) inflater.inflate(viewType, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        YeuCauDangKyGoi yeuCauDangKyGoi = list.get(position);

        holder.tvTen.setText(yeuCauDangKyGoi.getChuTro().getTen());
        holder.tvSoPhong.setText(yeuCauDangKyGoi.getGoi().getSoLuongPhongToiDa()+" phòng");
        holder.tvThoiGian.setText(yeuCauDangKyGoi.getGoi().getThoiHan()+" ngày");
        holder.tvGia.setText(yeuCauDangKyGoi.getGoi().getGia()+" giá");
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickItemListenner(position, v);
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

    public interface OnClick{
        void onClickItemListenner(int position, View v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View.OnClickListener onClickListener;
        TextView tvTen;
        TextView tvSoPhong;
        TextView tvThoiGian;
        TextView tvGia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvSoPhong = itemView.findViewById(R.id.tvSoPhong);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            tvGia = itemView.findViewById(R.id.tvGia);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
