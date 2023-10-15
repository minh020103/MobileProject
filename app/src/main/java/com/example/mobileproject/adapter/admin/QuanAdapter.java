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
import com.example.mobileproject.datamodel.TienIch;

import java.util.ArrayList;

public class QuanAdapter extends RecyclerView.Adapter<QuanAdapter.MyViewHolder> {

    ArrayList<TienIch> arrayList;
    Activity activity;
    int layoutId;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public QuanAdapter(ArrayList<TienIch> arrayList, Activity activity, int layoutId) {
        this.arrayList = arrayList;
        this.activity = activity;
        this.layoutId = layoutId;
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
        TienIch tienIch = arrayList.get(position);
        holder.tenQuan.setText(tienIch.getTenTienIch());

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.xem:
                        onClickListener.onClickXem(position,view);
                        break;
                    case R.id.sua:
                        onClickListener.onClickSua(position, view);
                        break;
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutId;
    }

    public interface OnClickListener{
        void onClickXem(int position, View view);
        void onClickSua(int position, View view);
    }
    protected class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgQuan;
        TextView tenQuan, tvXem, tvSua;

        View.OnClickListener onClickListener;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgQuan = itemView.findViewById(R.id.imgQuan);
            tenQuan = itemView.findViewById(R.id.tenQuan);
            tvXem = itemView.findViewById(R.id.xem);
            tvSua = itemView.findViewById(R.id.sua);

            tvXem.setOnClickListener(this);
            tvSua.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(view);
        }
    }
}
