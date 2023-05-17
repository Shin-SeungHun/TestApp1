package com.ssh.testapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// 하위 어답터
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ssh.testapp1.R;
import com.ssh.testapp1.activities.DetailActivity;


import java.util.List;

public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder> {

    private List<SubItem> subItemList;
    private LayoutInflater mInflate;
    private Context mContext;

    public SubItemAdapter(Context context, List<SubItem> subItemList) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.subItemList = subItemList;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflate.inflate(R.layout.layout_sub_item, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder holder, final int position) {
        String url = "https://image.tmdb.org/t/p/w500" + subItemList.get(position).getPoster_path();
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("title", subItemList.get(position).getTitle());
            intent.putExtra("original_title", subItemList.get(position).getOriginal_title());
            intent.putExtra("poster_path", subItemList.get(position).getPoster_path());
            intent.putExtra("overview", subItemList.get(position).getOverview());
            intent.putExtra("release_date", subItemList.get(position).getRelease_date());
            mContext.startActivity(intent);
            Log.d("Adapter", "Clicked: " + position);
        });
    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        SubItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}


