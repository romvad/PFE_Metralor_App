package com.example.rvadam.pfe.TestFirebaseStorage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.rvadam.pfe.Adapters.CustomChooseFileListAdapter;
import com.example.rvadam.pfe.Login.LoginContract;
import com.example.rvadam.pfe.Model.Constants;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.TestOneDrive.TestOneDriveActivity;
import com.example.rvadam.pfe.Utils.DownloadTask;
import com.example.rvadam.pfe.Utils.InternetConnectionTools;
import com.example.rvadam.pfe.Utils.MicrosoftLogin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalClientException;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.User;
import com.microsoft.onedrivesdk.picker.IPicker;
import com.microsoft.onedrivesdk.picker.IPickerResult;
import com.microsoft.onedrivesdk.picker.LinkType;
import com.microsoft.onedrivesdk.picker.Picker;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestFirebaseStorageBisActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int REQUEST_CODE_MICROSOFT_LOGIN = 1001;
    private static final int RESULT_OK_CODE_MICROSOFT_LOGIN = 2003;
    private static final int REQUEST_CODE_ONEDRIVE_PICKER = 61680;
    private static final int RESULT_OK_CODE_ONEDRIVE_PICKER = -1;
    private static final String TAG = "FirebaseStorageBisActiv";
    private ImageView imageView;
    private Button chooseButton;
    private Button uploadButton;

    private Uri filePath;
    private StorageReference storageReference;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    Constants constantsInstance = Constants.getInstance();
    /* Azure AD Variables */
    PublicClientApplication sampleApp;

    private boolean picker = false;
    private IPicker mPicker;

    //Name of file retrived from OneDrive
    private String strNameOfChoosedFile="";
    private TextView nameChoosedFile;
    private TextView statusChoosedFile;


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
        nameChoosedFile = (TextView) findViewById(R.id.nameChoosedFile);
        statusChoosedFile = (TextView) findViewById(R.id.statusChoosedFile);

        updateFileStatusTextView(R.string.status_choosed_file_NOT_UPLOADED);

        chooseButton.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
        uploadButton.setEnabled(false);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select an image"), PICK_IMAGE_REQUEST);
    }


    private void uploadFile() {
        //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        Log.i(TAG, "uploadFile called");
        if (filePath != null) {

            Log.i(TAG, " filePath not null");

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");

            if(InternetConnectionTools.isNetworkAvailable(getActivity())) {
                progressDialog.show();

            }else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.upload_file_waiting_internet_connection_1) +"\""+strNameOfChoosedFile+"\" "+getResources().getString(R.string.upload_file_waiting_internet_connection_2), Toast.LENGTH_LONG).show();
                updateFileStatusTextView(R.string.status_choosed_file_WAIT_FOR_INTERNET_CONNECTION);
            }


            StorageReference riversRef = storageReference.child("images/"+strNameOfChoosedFile);

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Le fichier \""+strNameOfChoosedFile+"\" a été uploadé avec succès", Toast.LENGTH_LONG).show();

                            //Case of an uploaded file from One Drive: temporary file deletion
                            if (constantsInstance.getTmpFileDLFromOneDrive().exists())
                                constantsInstance.getTmpFileDLFromOneDrive().delete();

                            //We disable the upload button and update the file status
                            uploadButton.setEnabled(false);
                            updateFileStatusTextView(R.string.status_choosed_file_UPLOADED);
                            //statusChoosedFile.setText(R.string.status_choosed_file_UPLOADED);

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

        Log.i(TAG,"onActivRes call with requestcode "+requestCode+" and resultcode "+resultCode+ " data "+data+" getData "+data.getData());

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            //Toast.makeText(getApplicationContext(),filePath.toString(),Toast.LENGTH_LONG);
            Log.i(TAG, filePath.toString());
            updateFileStatusTextView(R.string.status_choosed_file_READY);
            //statusChoosedFile.setText(R.string.status_choosed_file_READY);
            uploadButton.setEnabled(true);
            strNameOfChoosedFile=getFileName(filePath);
            nameChoosedFile.setText(strNameOfChoosedFile);
            /*try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        } else if (requestCode == REQUEST_CODE_MICROSOFT_LOGIN && resultCode == RESULT_OK_CODE_MICROSOFT_LOGIN && data != null ) {
            Log.i(TAG,"onActivityResult MICROSOFT LOGIN call");// end of login frame
            sampleApp.handleInteractiveRequestRedirect(requestCode, resultCode, data);
            Toast.makeText(getApplicationContext(),"Connexion au compte MICROSOFT réussie",Toast.LENGTH_LONG).show();

            //Now we can launch the download of the file from One Drive
            //downloadFileFromOneDrive();
        }else if (requestCode == REQUEST_CODE_ONEDRIVE_PICKER && resultCode == RESULT_OK_CODE_ONEDRIVE_PICKER && data != null && data.getData() != null) {
            //if(picker == true) {
                Log.i(TAG,"OnActivity result after start picking");
                // Get the results from the picker
                IPickerResult result = mPicker
                        .getPickerResult(requestCode, resultCode, data);

                // Handle the case if nothing was picked
                if (result != null) {

                    // Do something with the picked file
                    Log.e("main", "Link to file '" + result
                            .getName() + ": " + result.getLink());

                    strNameOfChoosedFile=result.getName();
                    nameChoosedFile.setText(strNameOfChoosedFile);

                    //((TextView) findViewById(R.id.pic_link))
                      //      .setText(result.getLink().toString());

                    // Launch donwload with the download link

                    // declare the dialog as a member field of your activity
                    ProgressDialog mProgressDialog;

                    // instantiate it within the onCreate method
                    mProgressDialog = new ProgressDialog(TestFirebaseStorageBisActivity.this);
                    mProgressDialog.setMessage("A message");
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mProgressDialog.setCancelable(true);

                    // execute this when the downloader must be fired
                    final DownloadTask downloadTask = new DownloadTask(TestFirebaseStorageBisActivity.this);
                    try {
                        downloadTask.execute(result.getLink().toString()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                    mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            downloadTask.cancel(true);
                        }
                    });

                    /*while (downloadTask.getStatus()== AsyncTask.Status.RUNNING){
                        Log.i(TAG,"dltask status: "+downloadTask.getStatus());
                    }*/

                    updateFileStatusTextView(R.string.status_choosed_file_READY);
                    //statusChoosedFile.setText(R.string.status_choosed_file_READY);
                    uploadButton.setEnabled(true);

                    //We store the file path of the temporary file (that has always the same name saved in Downloads directory in case of OneDrve selection
                    filePath = Uri.fromFile(constantsInstance.getTmpFileDLFromOneDrive());
                    return;
                }
            //}
        }
    }

    @Override
    public void onClick(View view) {
        if (view == chooseButton) {
            //open file chooser
            //showFileChooser();
            buildAlertDialog();
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

    private AppCompatActivity getActivity(){
        return this;
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
        final AlertDialog alertDialogCreated = alertDialog.create();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        //Toast.makeText(getApplicationContext(), "interne", Toast.LENGTH_LONG);
                        Log.i(TAG,"interne");
                        showFileChooser();
                        alertDialogCreated.dismiss();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "oneDrive", Toast.LENGTH_LONG);
                        Log.i(TAG,"oneDrive");
                        if(InternetConnectionTools.isNetworkAvailable(getActivity())){
                            microsoftAccountAuthentication();
                            alertDialogCreated.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(), R.string.download_from_one_drive_offline_impossible, Toast.LENGTH_LONG).show();
                        }

                        break;
                    default:
                        ;

                }
            }
            });
        //alertDialog.setItems()
        //final AlertDialog alertDialogCreated = alertDialog.create();

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

    private void microsoftAccountAuthentication(){

        MicrosoftLogin microsoftLogin = new MicrosoftLogin();

        sampleApp = microsoftLogin.microsoftAuthentication(this);

        downloadFileFromOneDrive();

    }

    private void downloadFileFromOneDrive(){
        picker = true;
        Log.i(TAG,"before start picker");
        if(InternetConnectionTools.isNetworkAvailable(this)) {
            mPicker = Picker.createPicker(constantsInstance.getOneDriveClientId());
            Log.i(TAG, "after start picker" + picker);
            mPicker.startPicking(this, LinkType.DownloadLink);

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

    private void updateFileStatusTextView(int statusResourceID){

        switch (statusResourceID){
            case R.string.status_choosed_file_NOT_UPLOADED:
                statusChoosedFile.setText(R.string.status_choosed_file_NOT_UPLOADED);
                statusChoosedFile.setBackgroundResource(R.color.colorStatusFileNotUploaded);
                break;
            case R.string.status_choosed_file_READY:
                statusChoosedFile.setText(R.string.status_choosed_file_READY);
                statusChoosedFile.setBackgroundResource(R.color.colorStatusFileReadyOrWaitingConnection);
                break;
            case R.string.status_choosed_file_WAIT_FOR_INTERNET_CONNECTION:
                statusChoosedFile.setText(R.string.status_choosed_file_WAIT_FOR_INTERNET_CONNECTION);
                statusChoosedFile.setBackgroundResource(R.color.colorStatusFileReadyOrWaitingConnection);
                break;
            case R.string.status_choosed_file_UPLOADED:
                statusChoosedFile.setText(R.string.status_choosed_file_UPLOADED);
                statusChoosedFile.setBackgroundResource(R.color.colorStatusFileUploaded);
                break;
            default:;

        }
    }

    /* Handles the redirect from the System Browser */
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG,"onActivityResult MICROSOFT LOGIN call");// end of login frame
        sampleApp.handleInteractiveRequestRedirect(requestCode, resultCode, data);
    }*/


}
