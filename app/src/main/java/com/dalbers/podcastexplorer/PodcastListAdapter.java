package com.dalbers.podcastexplorer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.dalbers.podcastexplorer.data.Podcast;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

/**
 * Created by DavidAlbers on 11/22/2016.
 */

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.ViewHolder> {
    private List<Podcast> podcasts;
    private PodcastItemListener listener;
    private HashMap<String, Bitmap> artwork = new HashMap<>();

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    public PodcastListAdapter(@NonNull PodcastItemListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.podcast_card,parent,false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(podcasts.get(position).getName());
        holder.description.setText(podcasts.get(position).getDesc());
        holder.name.setText(podcasts.get(position).getArtistName());
        String smallUrl = podcasts.get(position).getSmallArtwork();
        if(artwork.containsKey(smallUrl))
            holder.image.setImageBitmap(artwork.get(smallUrl));
        else
            (new DownloadImageTask()).executeOnExecutor(THREAD_POOL_EXECUTOR,smallUrl);
    }

    @Override
    public int getItemCount() {
        if(podcasts == null)
            return 0;
        return podcasts.size();
    }

    public Podcast getItemAt(int position) {
        return podcasts.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView name;
        TextView description;
        ImageView image;
        PodcastItemListener listener;

        ViewHolder(View itemView, @NonNull PodcastItemListener listener) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title_text);
            name = (TextView)itemView.findViewById(R.id.name_text);
            description = (TextView)itemView.findViewById(R.id.description_text);
            image = (ImageView)itemView.findViewById(R.id.image);
            itemView.setOnClickListener(onItemClickListener);
            this.listener = listener;
        }

        View.OnClickListener onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        };
    }


    public interface PodcastItemListener {
        void onClick(View view);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        String url;

        protected Bitmap doInBackground(String... urls) {
            url = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            artwork.put(url,result);
            for(int i = 0; i < podcasts.size(); i++) {
                if(podcasts.get(i).getSmallArtwork().equals(url))
                notifyItemChanged(i);
            }
        }
    }

}
