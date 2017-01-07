package com.dalbers.podcastexplorer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Data.Podcast;

/**
 * Created by DavidAlbers on 11/22/2016.
 */

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.ViewHolder> {
    List<Podcast> podcasts;

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.podcast_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(podcasts.get(position).getTitle());
        holder.description.setText(podcasts.get(position).getDesc());
        holder.name.setText(podcasts.get(position).getName().toString());
    }

    @Override
    public int getItemCount() {
        if(podcasts == null)
            return 0;
        return podcasts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView name;
        public TextView description;
        public ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title_text);
            name = (TextView)itemView.findViewById(R.id.name_text);
            description = (TextView)itemView.findViewById(R.id.description_text);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }

}
