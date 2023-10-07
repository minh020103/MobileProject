package com.example.mobileproject.recycerviewadapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.Banner;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private Context mContext;
    private List<Banner> mListBanner;

    public BannerAdapter(Context mContext, List<Banner> mListBanner) {
        this.mContext = mContext;
        this.mListBanner = mListBanner;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_admin_management_banner_layout, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = mListBanner.get(position);
        if (banner == null) {
            return;
        }
        holder.imgBanner.setImageResource(banner.getResourceId());
        holder.tvName.setText(banner.getName());
    }

    @Override
    public int getItemCount() {
        if (mListBanner != null) {
            return mListBanner.size();
        }
        return 0;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layoutItem;
        private ImageView imgBanner;
        private TextView tvName;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItemBanner);
            imgBanner = itemView.findViewById(R.id.layoutItemBanner);
            tvName = itemView.findViewById(R.id.tvNameBanner);
        }
    }
}
