package com.example.rvadam.pfe.TestFirebaseStorage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rvadam.pfe.Adapters.CustomChooseFileListAdapter;
import com.example.rvadam.pfe.Login.LoginContract;
import com.example.rvadam.pfe.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFirebaseStorageBisActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 234;
    private static final String TAG = "FirebaseStorageBisActiv";
    private ImageView imageView;
    private Button chooseButton;
    private Button uploadButton;

    private Uri filePath;
    private StorageReference storageReference;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_firebase_storage_bis);

        FirebaseUser user = mAuth.getCurrentUser();
        //Log.i(TAG,"user current "+user.toString());
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }

        storageReference = FirebaseStorage.getInstance().getReference();

        Log.i(TAG, "storageReference " + storageReference.toString());
        imageView = (ImageView) findViewById(R.id.imageView);
        chooseButton = (Button) findViewById(R.id.chooseButton);
        uploadButton = (Button) findViewById(R.id.uploadButton);

        chooseButton.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select an image"), PICK_IMAGE_REQUEST);
    }

   /* @Override
    protected Dialog onCreateDialog(int id){
        return null;
    }*/

    private void uploadFile() {
        //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        Log.i(TAG, "uploadFile called");
        if (filePath != null) {

            Log.i(TAG, " filePath not null");

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference riversRef = storageReference.child("images/test2.jpg");

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File uploaded", Toast.LENGTH_LONG);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG);
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    //Log.i(TAG,"progress : "+progress);
                    progressDialog.setMessage(((int) progress) + "% uploaded...");
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            //Toast.makeText(getApplicationContext(),filePath.toString(),Toast.LENGTH_LONG);
            Log.i(TAG, filePath.toString());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View view) {
        if (view == chooseButton) {
            //open file chooser
            showFileChooser();
            //buildAlertDialog();
        } else if (view == uploadButton) {
            //upload file to firebase storage
            Log.i(TAG, "uploadButtonClicked");
            uploadFile();

        }
    }

    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                    }
                });
    }

    private void buildAlertDialog() {
        List<String> listOptions = new ArrayList<String>();
        listOptions.add("Interne");
        listOptions.add("OneDrive");
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(TestFirebaseStorageBisActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.list_choosefile_dialog, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(R.string.title_choice_file);
        ListView lv = (ListView) convertView.findViewById(R.id.listViewChooseFileDialog);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        CustomChooseFileListAdapter adapter = new CustomChooseFileListAdapter(this, android.R.layout.simple_list_item_1, listOptions);
        lv.setAdapter(adapter);
        alertDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "interne", Toast.LENGTH_LONG);
                        Log.i(TAG,"interne");
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "oneDrive", Toast.LENGTH_LONG);
                        Log.i(TAG,"oneDrive");
                        break;
                    default:
                        ;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        //alertDialog.setItems()
        final AlertDialog alertDialogCreated = alertDialog.create();

       /*alertDialog.setPositiveButton(R.string.cancel_alert_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogCreated.dismiss();
            }

        });*/

        alertDialogCreated.setButton(DialogInterface.BUTTON_POSITIVE,"annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogCreated.dismiss();
            }

        });


        alertDialogCreated.show();
        /*final Button positiveButton = alertDialogCreated.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);*/

        //alertDialogCreated.show();
        Log.i(TAG, "builder alert dialog called");
    }
}
