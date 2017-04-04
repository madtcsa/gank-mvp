package com.madchen.gank_mvp.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.madchen.gank_mvp.R;
import com.madchen.gank_mvp.model.Ganks;

import java.util.List;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GanksListAdapter extends RecyclerView.Adapter<GanksListAdapter.MyViewHolder> {

    private Context mContext;
    private List<Ganks.Results> mResultsList;

    public GanksListAdapter(Context context, List<Ganks.Results> resultsList) {
        this.mContext = context;
        this.mResultsList = resultsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ganks_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ganks.Results results = mResultsList.get(position);
        holder.authorText.setText(results.getWho());
        holder.createdTimeText.setText(results.getCreatedAt());
        holder.descText.setText(results.getDesc());
        if (results.getImgUrls() != null && results.getImgUrls().size() >= 0) {
            Log.d("GanksListAdapter", "----imgUrl---- " + results.getImgUrls().get(0));
            Glide.with(mContext).load(results.getImgUrls().get(0)).into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mResultsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView descText, createdTimeText, authorText;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img_item);
            descText = (TextView) itemView.findViewById(R.id.text_desc_item);
            createdTimeText = (TextView) itemView.findViewById(R.id.text_created_time);
            authorText = (TextView) itemView.findViewById(R.id.text_author);
        }
    }

    public void setResultsList(List<Ganks.Results> resultsList) {
        mResultsList = resultsList;
        notifyDataSetChanged();
    }
}
