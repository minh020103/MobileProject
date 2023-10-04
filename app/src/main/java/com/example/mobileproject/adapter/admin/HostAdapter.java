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

public class HostAdapter extends RecyclerView.Adapter<HostAdapter.MyViewHolder> {

    private Activity activity;
    private List<HostModel> list;
    private int layoutID;

    OnClickItemListener onClickItemListene;

    public HostAdapter(Activity activity, List<HostModel> list, int layoutID) {
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
        Glide.with(activity.getLayoutInflater().getContext()).load(list.get(position).getHinhNguoiDung()).into(holder.imgHost);
        holder.tvNameHost.setText(data.getTenNguoiDung());
        holder.tvPhoneHost.setText(data.getSoDienThoai());
        if (data.getXacThuc() == 1)
        {
            holder.tvStatusHost.setText("Da Xac Thuc");
            holder.tvStatusHost.setBackgroundColor(0xFF00FF00);
        }
        else
        {
            holder.tvStatusHost.setText("Chua Xac Thuc");
            holder.tvStatusHost.setBackgroundColor(0xFFFF0000);
        }
        //holder.tvStatusHost.setText(String.valueOf(data.getXacThuc()));

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
        TextView tvNameHost;
        ImageView imgHost;
        TextView tvPhoneHost;
        TextView tvStatusHost;

        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameHost = itemView.findViewById(R.id.tvNameHost);
            imgHost = itemView.findViewById(R.id.imgHost);
            tvPhoneHost = itemView.findViewById(R.id.tvPhoneHost);
            tvStatusHost = itemView.findViewById(R.id.tvStatusHost);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
