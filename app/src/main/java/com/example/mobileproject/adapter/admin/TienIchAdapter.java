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

public class TienIchAdapter extends RecyclerView.Adapter<TienIchAdapter.MyViewHolder> {

    ArrayList<TienIch> arrayList;
    Activity activity;
    int layoutId;

    public TienIchAdapter(ArrayList<TienIch> arrayList, Activity activity, int layoutId) {
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
        holder.textView.setText(tienIch.getTenTienIch());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutId;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imgTienIch);
            textView = itemView.findViewById(R.id.tenTienIch);
        }
    }
}
