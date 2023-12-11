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
import com.example.mobileproject.datamodel.Phuong;

import java.util.List;

public class WardAdapter extends RecyclerView.Adapter<WardAdapter.MyViewHolder> {

    private Activity activity;
    private List<Phuong> list;
    private int layoutID;
    private MyOnClickListenner myOnClickListenner;


    public WardAdapter(Activity activity, List<Phuong> list, int layoutID) {
        this.activity = activity;
        this.list = list;
        this.layoutID = layoutID;
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
        Phuong phuong = list.get(position);
        holder.tvPhuong.setText(phuong.getTenPhuong());
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickListenner.onClickItemListener(position, v);
            }
        };

    }

    public void setMyOnClickListenner(MyOnClickListenner myOnClickListenner) {
        this.myOnClickListenner = myOnClickListenner;
    }

    public interface MyOnClickListenner{
        void onClickItemListener(int position, View v);
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

        TextView tvPhuong;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPhuong = itemView.findViewById(R.id.tvPhuong);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
