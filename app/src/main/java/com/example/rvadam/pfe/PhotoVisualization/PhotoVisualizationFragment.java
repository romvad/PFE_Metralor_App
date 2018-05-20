package com.example.rvadam.pfe.PhotoVisualization;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rvadam.pfe.AppGlideModule.GlideApp;
import com.example.rvadam.pfe.CameraFunctionality.CameraFunctionalityActivity;
import com.example.rvadam.pfe.Model.FileStatus;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.PhotoHandler.PictureHistory;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.TestLoginWithMSAL.LoginActivity;
import com.example.rvadam.pfe.Utils.DocumentsManager;
import com.example.rvadam.pfe.Utils.InternetConnectionTools;
import com.example.rvadam.pfe.Utils.WorkSitesManager;
import com.example.rvadam.pfe.WorkSiteListOfDocuments.FakeListWorksitesActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by rvadam on 19/05/2018.
 */

public class PhotoVisualizationFragment extends Fragment {

    private static final String TAG= "PhotoVisualizationFrag";
    private static final int PICK_IMAGE_REQUEST =234 ;
    private static final int TAKE_PHOTO_REQUEST=100;
    public static final String PHOTO_NAME_KEY="photo name key";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView mImageView;
    private Button takePhotoButton;
    private Button chooseExistingPhotoButton;
    private Button uploadChoosedPhotoButton;
    private TextView statusPhoto;
    private Uri filePath;
    private boolean isPhotoUploaded;

    private StorageReference fullRef;

    private ProgressDialog progressDialog;

    private SpacePhoto photoAtStake;
    String photoLocalName;//Name of the photo locally stored after taking it with camera

    public PhotoVisualizationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_visualization, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView = (ImageView) view.findViewById(R.id.image);
        takePhotoButton=(Button) view.findViewById(R.id.takePhotoButton) ;
        chooseExistingPhotoButton=(Button) view.findViewById(R.id.chooseExistingPhotoButton);
        uploadChoosedPhotoButton=(Button) view.findViewById(R.id.uploadChoosedPhotoButton);
        statusPhoto=(TextView) view.findViewById(R.id.photoStatus);

        Bundle bundle = getArguments();
        photoAtStake = bundle.getParcelable(PhotoVisualizationActivity.SELECTED_PHOTO);

        StorageReference ref= FirebaseStorage.getInstance().getReference();
        String relativePath= photoAtStake.getmFBStorageUrl();
        String photoIdWorksite=photoAtStake.getmIdWorkSite();
        final String absolutePath=photoIdWorksite+"/"+relativePath;
        fullRef=ref.child(absolutePath);
        //Log.i(TAG,"storage ref "+fullRef);

        displayPhotoWithStorageReference(fullRef);
        //displayPhotoWithStorageReference(ref);
        checkPhotoUploaded();
        if(isPhotoUploaded){
            updatePhotoStatus(FileStatus.UPLOADED);
        } else {
            updatePhotoStatus(FileStatus.NOT_UPLOADED);
        }


        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"take photo button call");
               Intent intent = new Intent(getActivity(), CameraFunctionalityActivity.class);
                Log.i(TAG,"after new intent");
                Log.i(TAG,"worksite id photo "+photoAtStake.getmIdWorkSite());
               WorkSite w=WorkSitesManager.getWorkSiteById(photoAtStake.getmIdWorkSite());
               photoLocalName= w.getName()+"-"+photoAtStake.getTitle();
               intent.putExtra(PHOTO_NAME_KEY, photoLocalName);
               startActivityForResult(intent,TAKE_PHOTO_REQUEST);
                //startActivity(intent);
                Log.i(TAG,"after start activity");
               /* Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.i(TAG,"take picture intent");
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }*/
            }
        });

        chooseExistingPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select an image"), PICK_IMAGE_REQUEST);
            }

        });

        uploadChoosedPhotoButton.setEnabled(false);
        uploadChoosedPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });
    }

    private void displayPhotoWithStorageReference(StorageReference fullRef) {
        GlideApp.with(getActivity().getApplicationContext())
                .load(fullRef)
                .placeholder(R.mipmap.ic_image_not_available)
                .error(R.mipmap.ic_image_not_available)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath=data.getData();
            displayPhotoWithUri(filePath);

        }else if (requestCode==TAKE_PHOTO_REQUEST && resultCode == RESULT_OK){
            filePath=PictureHistory.getInstance().getLastPicturePathUri();
            displayPhotoWithUri(filePath);
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
        uploadChoosedPhotoButton.setEnabled(true);
    }

    private void displayPhotoWithUri(Uri data) {

        GlideApp.with(getActivity().getApplicationContext())
                .load(data)
                .placeholder(R.mipmap.ic_image_not_available)
                .error(R.mipmap.ic_image_not_available)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }

    private void uploadPhoto() {

        displayUploadEvolution();

        fullRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), "Le fichier \"" + photoAtStake.getTitle() + "\" a été uploadé avec succès", Toast.LENGTH_LONG).show();
                updatePhotoStatus(FileStatus.UPLOADED);
                uploadChoosedPhotoButton.setEnabled(false);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressDialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG);
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                //Log.i(TAG,"progress : "+progress);
                progressDialog.setMessage(((int) progress) + "% uploaded...");
            }
        });

        askingPhotoLocalStoreAlertDialog();

    }

    private void askingPhotoLocalStoreAlertDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final AlertDialog alertDialog=alertDialogBuilder.create();

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }

        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();

                File path = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File fileToDelete= new File(path, DocumentsManager.getFileName(filePath,getActivity().getContentResolver()));
                if(fileToDelete.exists()){
                    fileToDelete.delete();
                }
            }

        });

        alertDialog.setTitle(getResources().getString(R.string.title_local_store_photo_alert_dialog));
        alertDialog.setMessage(getResources().getString(R.string.message_local_store_photo_alert_dialog));
    }

    private void updatePhotoStatus(FileStatus status){

        String strStatus;
        switch (status){
            case READY:
                strStatus=getResources().getString(R.string.status_choosed_file_READY);
                break;
            case UPLOADED:
                strStatus=getResources().getString(R.string.status_choosed_file_UPLOADED);
                break;
            case WAIT_FOR_INTERNET_CONNECTION:
                strStatus=getResources().getString(R.string.status_choosed_file_WAIT_FOR_INTERNET_CONNECTION);
                break;
            case NOT_UPLOADED:
                strStatus=getResources().getString(R.string.status_choosed_file_NOT_UPLOADED);
                break;
            default:strStatus="-";

        }

        statusPhoto.setText(strStatus);

    }

    private void displayUploadEvolution(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading...");

        PhotoVisualizationActivity photoVisualizationActivity = (PhotoVisualizationActivity) getActivity();

        if(InternetConnectionTools.isNetworkAvailable(photoVisualizationActivity.getActivity())) {
            progressDialog.show();

        }else {
            Toast.makeText(photoVisualizationActivity.getApplicationContext(), getResources().getString(R.string.upload_file_waiting_internet_connection_1) +"\""+ photoAtStake.getTitle()+"\" "+getResources().getString(R.string.upload_file_waiting_internet_connection_2), Toast.LENGTH_LONG).show();

        }
    }

    private void checkPhotoUploaded() {
        fullRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                isPhotoUploaded = true;
            }
        });
    }

}
