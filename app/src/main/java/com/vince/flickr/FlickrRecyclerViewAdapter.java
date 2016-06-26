package com.vince.flickr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vince on 16-05-09.
 */
public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrImageViewHolder> {
    //section 9 lecture 68 "Continue Creating the basic gui"

    private List<Photo> mPhotoList;
    private Context mContext;
    private final String LOG_TAG = FlickrRecyclerViewAdapter.class.getSimpleName();

    public FlickrRecyclerViewAdapter(Context mContext, List<Photo> mPhotoList) {
        this.mPhotoList = mPhotoList;
        this.mContext = mContext;
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, null);
        FlickrImageViewHolder flickrImageViewHolder = new FlickrImageViewHolder(view);
        return flickrImageViewHolder;
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position) {

        Photo photoItem = mPhotoList.get(position);
        Log.d(LOG_TAG, "Processing: " + photoItem.getmTitle() + " ===> " + Integer.toString(position));
        Picasso.with(mContext).load(photoItem.getmImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);
        holder.title.setText(photoItem.getmTitle());
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */

    @Override
    public int getItemCount() {
        return (null != mPhotoList ? mPhotoList.size() : 0);
    }

    public void loadNewData(List<Photo> newPhotos) {
        mPhotoList = newPhotos;
        notifyDataSetChanged(); // reprocess the file and redraw it on the view
    }

    public Photo getPhoto(int position) {
        return (null != mPhotoList ? mPhotoList.get(position) : null);
    }
}
