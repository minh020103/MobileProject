package com.example.mobileproject.recycerviewadapter.admin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.Banner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private Activity activity;
    private List<Banner> mListBanner;
    private int layoutID;
    private MyOnCLickListener myOnCLickListener;


    public BannerAdapter(Activity activity, List<Banner> mListBanner, int layoutID) {
        this.activity = activity;
        this.mListBanner = mListBanner;
        this.layoutID = layoutID;
    }

    public void setMyOnCLickListener(MyOnCLickListener myOnCLickListener) {
        this.myOnCLickListener = myOnCLickListener;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        CardView view = (CardView) inflater.inflate(viewType, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = mListBanner.get(position);

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnCLickListener.OnClickItem(position, v);
            }
        };

    }

    @Override
    public int getItemCount() {
        if (mListBanner != null) {
            return mListBanner.size();
        }
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public interface MyOnCLickListener{
        void OnClickItem(int position, View v);
        void OnClickFabAdd(int position,View v);
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View.OnClickListener onClickListener;

        private ImageView imgBanner;
        private TextView tvName;
        private FloatingActionButton fabAdd;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.imgBanner);
            tvName = itemView.findViewById(R.id.tvNameBanner);
            fabAdd = itemView.findViewById(R.id.fabAdd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
}
