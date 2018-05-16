package com.example.rvadam.pfe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
//import com.example.rvadam.pfe.AppGlideModule.GlideApp;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rvadam.pfe.AppGlideModule.GlideApp;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WorkSitePhotoGallery.PhotoGalleryActivity;
import com.example.rvadam.pfe.WorkSitePhotoGallery.SpacePhotoActivity;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


/**
 * Created by rvadam on 15/05/2018.
 */

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {

    private List<SpacePhoto> mSpacePhotos;
    private Context mContext;
    private static final String TAG="ImageGalleryAdapter";

    ImageView imageView;


    @Override
    public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View photoView = inflater.inflate(R.layout.item_photo_gallery, parent, false);
        ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

        SpacePhoto spacePhoto = mSpacePhotos.get(position);
       // ImageView imageView = holder.mPhotoImageView;*/
       // SpacePhoto photo = mPhotoList.get(position);
        imageView = holder.mPhotoImageView;

        StorageReference ref= FirebaseStorage.getInstance().getReference();
        String relativePath= spacePhoto.getmFBStorageUrl();
        StorageReference fullRef=ref.child(relativePath);

        Log.i(TAG,"storage ref "+fullRef);


        GlideApp.with(mContext)
                .load(fullRef)
                .placeholder(R.mipmap.ic_cloud_off_red)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (mSpacePhotos.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mPhotoImageView;

        public MyViewHolder(View itemView) {

            super(itemView);
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                SpacePhoto spacePhoto = mSpacePhotos.get(position);
                Intent intent = new Intent(mContext, SpacePhotoActivity.class);
                intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, spacePhoto);
                mContext.startActivity(intent);
            }
        }
    }

    public ImageGalleryAdapter(Context context, List<SpacePhoto> spacePhotos) {
        mContext = context;
        mSpacePhotos = spacePhotos;
    }
}

