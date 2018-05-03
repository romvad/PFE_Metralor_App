package com.example.rvadam.pfe.WorkSite;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rvadam.pfe.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//import static com.google.android.gms.common.GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
//import static com.google.android.gms.common.GooglePlayServicesUtilLight.isGooglePlayServicesAvailable;

public class WorkSiteActivity extends AppCompatActivity {

    Button uploadHuissier;
    private static final String TAG="WorkSiteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_site);

        uploadHuissier=(Button) findViewById(R.id.huissierUpload);

        uploadHuissier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            //selectedfile.getPath()
            Log.i(TAG,"uri du fichier: "+getFileName(selectedfile));
            InputStream stream;

            FirebaseStorage storage=FirebaseStorage.getInstance();
            StorageReference storageRef=storage.getReference("ab/photos/adductions/energie");
            StorageReference storageRef2=storageRef.child(getFileName(selectedfile));

            //Log.i(TAG,"google play available "+isGooglePlayServicesAvailable (getBaseContext(),21));
            //Log.i (TAG, "gps version code : "+GOOGLE_PLAY_SERVICES_VERSION_CODE);

            Log.i(TAG,"directory downlaods "+getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
            //File file=new File("/root/sdcaezqesdfrd/Downloads/"+getFileName(selectedfile));
            UploadTask uploadTask=storageRef2.putFile(selectedfile);
            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    Log.i(TAG,"upload file failure: "+exception.toString());
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Log.i(TAG,"upload success");
                }
            });
            /*try {
                //stream = new FileInputStream(new File(selectedfile.getPath().toString()));
                //stream = new FileInputStream(new File("/root/sdcard/Downloads/"+getFileName(selectedfile)));

                //stream = new FileInputStream(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/"+getFileName(selectedfile));
                UploadTask uploadTask=storageRef2.putStream(stream);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/
        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
