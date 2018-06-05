package com.example.rvadam.pfe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.rvadam.pfe.AppGlideModule.GlideApp;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rvadam.pfe.AppGlideModule.GlideApp;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.PhotoVisualization.PhotoVisualizationActivity;
import com.example.rvadam.pfe.Utils.InternetConnectionTools;
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
    TextView photoName;
    SpacePhoto spacePhoto;


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

        spacePhoto = mSpacePhotos.get(position);
       // ImageView imageView = holder.mPhotoImageView;*/
       // SpacePhoto photo = mPhotoList.get(position);
        imageView = holder.mPhotoImageView;

        Log.i(TAG,"before getting storage reference");
        StorageReference ref= FirebaseStorage.getInstance().getReference();
        String relativePath= spacePhoto.getmFBStorageUrl();
        String photoIdWorksite=spacePhoto.getmIdWorkSite();
        String absolutePath=photoIdWorksite+"/"+relativePath;
        StorageReference fullRef=ref.child(absolutePath);

        photoName.setText(spacePhoto.getTitle());

        //Log.i(TAG,"storage ref "+fullRef);

        //To avoid displaying a former picture for an item because of the cache storing, we decide to deactivate cache when there is internet connection
        if(!InternetConnectionTools.isNetworkAvailable((AppCompatActivity)mContext)){
            GlideApp.with(mContext)
                    .load(fullRef)
                    .placeholder(R.mipmap.ic_image_not_available)
                    .error(R.mipmap.ic_image_not_available)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        } else {
            GlideApp.with(mContext)
                    .load(fullRef)
                    .placeholder(R.mipmap.ic_image_not_available)
                    .error(R.mipmap.ic_image_not_available)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView);
        }

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
            photoName = (TextView) itemView.findViewById(R.id.photoTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                SpacePhoto spacePhoto = mSpacePhotos.get(position);
                Intent intent = new Intent(mContext, PhotoVisualizationActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable(PhotoVisualizationActivity.EXTRA_SPACE_PHOTO,spacePhoto);
                intent.putExtra(PhotoVisualizationActivity.EXTRA_SPACE_PHOTO, bundle);
                mContext.startActivity(intent);
            }
        }
    }

    public ImageGalleryAdapter(Context context, List<SpacePhoto> spacePhotos) {
        mContext = context;
        mSpacePhotos = spacePhotos;
    }
}

