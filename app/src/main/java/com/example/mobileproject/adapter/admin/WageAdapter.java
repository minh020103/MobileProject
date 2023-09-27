package com.example.mobileproject.adapter.admin;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.Luong;

import java.util.List;

public class WageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private List<Luong> list;
    private int layoutID;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgWage;
        TextView tvName;
        TextView tvPhone;
        TextView tvWage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWage = itemView.findViewById(R.id.imgWage);
        }
    }
}
