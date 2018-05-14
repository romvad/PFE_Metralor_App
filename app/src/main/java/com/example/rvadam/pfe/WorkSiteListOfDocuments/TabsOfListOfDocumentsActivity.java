package com.example.rvadam.pfe.WorkSiteListOfDocuments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBDocumentsHelpers;
import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBDocumentsHelpers;
import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBWorkSitesHelper;
import com.example.rvadam.pfe.Model.Constants;
import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.DocumentTypes;
import com.example.rvadam.pfe.Model.FileStatus;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.TestFirebaseStorage.TestFirebaseStorageBisActivity;
import com.example.rvadam.pfe.Utils.DocumentsManager;
import com.example.rvadam.pfe.Utils.DownloadTask;
import com.example.rvadam.pfe.Utils.InternetConnectionTools;
import com.example.rvadam.pfe.Utils.MicrosoftLogin;
import com.example.rvadam.pfe.Utils.WorkSitesManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.onedrivesdk.picker.IPicker;
import com.microsoft.onedrivesdk.picker.IPickerResult;
import com.microsoft.onedrivesdk.picker.LinkType;
import com.microsoft.onedrivesdk.picker.Picker;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TabsOfListOfDocumentsActivity extends AppCompatActivity {

    private static final String TAG = "TabsOfListOfDocuments";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int REQUEST_CODE_MICROSOFT_LOGIN = 1001;
    private static final int RESULT_OK_CODE_MICROSOFT_LOGIN = 2003;
    private static final int REQUEST_CODE_ONEDRIVE_PICKER = 61680;
    private static final int RESULT_OK_CODE_ONEDRIVE_PICKER = -1;

    private  static final String STATUS_UPDATE="update";
    private  static final String NAME_CHOOSED_FILE_UPDATE="choose file";

    //private Uri filePath;
    private StorageReference storageReference;

    Constants constantsInstance = Constants.getInstance();

    PublicClientApplication sampleApp;

    //Fragments
    PlanDocumentsFragment planDocumentsFragment;
    SecurityDocumentsFragment securityDocumentsFragment;
    OtherDocumentsFragment otherDocumentsFragment;


    //Lists of documents
    ArrayList<Document> otherDocumentsList;
    ArrayList<Document> securityDocumentsList;
    ArrayList<Document> ppspsDocumentsList;
    ArrayList<Document> planDocumentsList;

    //Name of file retrived from OneDrive
    private String strNameOfChoosedFile="";
    private TextView nameChoosedFile;
    private TextView statusChoosedFile;

    private IPicker mPicker;

    int positionChoosedDocument=-1;
    int typeOfChoosedDocument=-1;

    String idWorkSite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_of_list_of_documents);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Retrivement of worksites list, normally called in List of Worksites activity
       // FirebaseDBWorkSitesHelper.getListOfWorkSites();

        //we suppose we create this activity with the following work site ID
        idWorkSite="-LBw-rNjtmo9G70LUU2Z";

        //Store of our firebase DB reference
        storageReference = FirebaseStorage.getInstance().getReference();


        //Retrivement of the lists of documents
       FirebaseDBDocumentsHelpers dbDocumentsHelpers= new FirebaseDBDocumentsHelpers(idWorkSite,this);
        //securityDocumentsList= FirebaseDBHelpers.getListOfDocuments("securityDocuments",idWorkSite);
        //planDocumentsList= FirebaseDBHelpers.getListOfDocuments("planDocuments",idWorkSite);
        //ppspsDocumentsList= FirebaseDBHelpers.getListOfDocuments("ppspsDocuments",idWorkSite);

/*        Log.i(TAG,"other size "+otherDocumentsList.size());
        Log.i(TAG,"secu size "+otherDocumentsList.size());
        Log.i(TAG,"plan size "+otherDocumentsList.size());
        Log.i(TAG,"ppsps size "+otherDocumentsList.size());*/

       /* //Lists initialization
        planDocumentsList=new ArrayList<Document>();
        otherDocumentsList=new ArrayList<Document>();
        securityDocumentsList=new ArrayList<Document>();*/

        //Instanciation of the fragments
        planDocumentsFragment=new PlanDocumentsFragment();
        Bundle planDocBundle=new Bundle();
        planDocBundle.putString("idWorkSite",idWorkSite);
        planDocumentsFragment.setArguments(planDocBundle);
        securityDocumentsFragment= new SecurityDocumentsFragment();
        Bundle securityDocBundle=new Bundle();
        securityDocBundle.putString("idWorkSite",idWorkSite);
        securityDocumentsFragment.setArguments(securityDocBundle);
        otherDocumentsFragment = new OtherDocumentsFragment();
        Bundle otherDocBundle=new Bundle();
        otherDocBundle.putString("idWorkSite",idWorkSite);
        otherDocumentsFragment.setArguments(otherDocBundle);

        //setup of the tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*Log.i(TAG,"other size "+otherDocumentsList.size());
        Log.i(TAG,"secu size "+securityDocumentsList.size());
        Log.i(TAG,"plan size "+planDocumentsList.size());
        Log.i(TAG,"ppsps size "+ppspsDocumentsList.size());*/



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(planDocumentsFragment, getResources().getString(R.string.tab_plan_documents));
        adapter.addFragment(securityDocumentsFragment, getResources().getString(R.string.tab_security_documents));
        adapter.addFragment(otherDocumentsFragment, getResources().getString(R.string.tab_other_documents));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Log.i(TAG,"onActivRes call with requestcode "+requestCode+" and resultcode "+resultCode+ " data "+data+" getData "+data.getData());

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //filePath = data.getData();
            //Toast.makeText(getApplicationContext(),filePath.toString(),Toast.LENGTH_LONG);
            //Log.i(TAG, filePath.toString());
            //updateFileStatusTextView(R.string.status_choosed_file_READY);
           /* positionChoosedDocument= data.getIntExtra("position doc",-1);
            typeOfChoosedDocument= data.getIntExtra("type of doc",-1);*/

            //strNameOfChoosedFile=getFileName(filePath);
            if(positionChoosedDocument!=-1 && typeOfChoosedDocument!=-1){
                strNameOfChoosedFile=updateFilePathDocument(DocumentTypes.forValue(typeOfChoosedDocument),positionChoosedDocument,data.getData(),true,idWorkSite);
                updateStatusDocument(DocumentTypes.forValue(typeOfChoosedDocument),positionChoosedDocument,idWorkSite,FileStatus.READY);
                updateNameChooseFileDocument(DocumentTypes.forValue(typeOfChoosedDocument),positionChoosedDocument,idWorkSite,strNameOfChoosedFile);
            }


            //strNameOfChoosedFile=getFileName(filePath);
            //nameChoosedFile.setText(strNameOfChoosedFile);


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
               // nameChoosedFile.setText(strNameOfChoosedFile);
                if(positionChoosedDocument!=-1 && typeOfChoosedDocument!=-1){
                    updateNameChooseFileDocument(DocumentTypes.forValue(typeOfChoosedDocument),positionChoosedDocument,idWorkSite,strNameOfChoosedFile);
                }


                // declare the dialog as a member field of your activity
                ProgressDialog mProgressDialog;

                // instantiate it within the onCreate method
                mProgressDialog = new ProgressDialog(TabsOfListOfDocumentsActivity.this);
                mProgressDialog.setMessage("A message");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(true);

                // execute this when the downloader must be fired
                final DownloadTask downloadTask = new DownloadTask(TabsOfListOfDocumentsActivity.this);
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

                //We update status and store the file path of the temporary file (that has always the same name saved in Downloads directory in case of OneDrve selection
                if(positionChoosedDocument!=-1 && typeOfChoosedDocument!=-1){
                    updateFilePathDocument(DocumentTypes.forValue(typeOfChoosedDocument),positionChoosedDocument,Uri.fromFile(constantsInstance.getTmpFileDLFromOneDrive()),false,idWorkSite);
                    updateStatusDocument(DocumentTypes.forValue(typeOfChoosedDocument),positionChoosedDocument,idWorkSite,FileStatus.READY);
                }

                return;
            }
            //}
        }
    }

    public void startDownloadFileFromOneDrive(int positionDocument,int typeOfChoosedDocument){
        microsoftAccountAuthentication();

        downloadFileFromOneDrive(positionDocument,typeOfChoosedDocument);

    }

    public void microsoftAccountAuthentication(){

        MicrosoftLogin microsoftLogin = new MicrosoftLogin();

        sampleApp = microsoftLogin.microsoftAuthentication(this);

    }

    private void downloadFileFromOneDrive(int positionDocument,int typeOfDocument){
       // picker = true;
        positionChoosedDocument=positionDocument;
        typeOfChoosedDocument=typeOfDocument;
        Log.i(TAG,"before start picker");
        if(InternetConnectionTools.isNetworkAvailable(this)) {
            mPicker = Picker.createPicker(constantsInstance.getOneDriveClientId());
            //Log.i(TAG, "after start picker" + picker);
            mPicker.startPicking(this, LinkType.DownloadLink);

        }
    }
    private AppCompatActivity getActivity(){
        return this;
    }

    public void uploadFile(final DocumentTypes typeOfDoc, final int positionDocumentToUpload) {
        //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        final Document docToUpload=getDocumentByPositionAndType(typeOfDoc,positionDocumentToUpload,idWorkSite);
        Log.i(TAG, "uploadFile called");
        Uri filePath=docToUpload.getFilePath();
        if (docToUpload!=null &&filePath!= null) {

            Log.i(TAG, " filePath not null");

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");

            if(InternetConnectionTools.isNetworkAvailable(getActivity())) {
                progressDialog.show();

            }else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.upload_file_waiting_internet_connection_1) +"\""+DocumentsManager.getFileName(filePath,getContentResolver())+"\" "+getResources().getString(R.string.upload_file_waiting_internet_connection_2), Toast.LENGTH_LONG).show();
                updateStatusDocument(typeOfDoc,positionDocumentToUpload,idWorkSite,FileStatus.WAIT_FOR_INTERNET_CONNECTION);
                //updateFileStatusTextView(R.string.status_choosed_file_WAIT_FOR_INTERNET_CONNECTION);
            }

            String nameWorkSite= WorkSitesManager.getWorkSiteById(idWorkSite).getName();

            StorageReference riversRef = storageReference.child(nameWorkSite+"/documents/"+String.valueOf(typeOfDoc)+"/"+docToUpload.getNameChoosedFile());

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Le fichier \""+docToUpload.getNameChoosedFile()+"\" a été uploadé avec succès", Toast.LENGTH_LONG).show();

                            //Case of an uploaded file from One Drive: temporary file deletion
                            if (constantsInstance.getTmpFileDLFromOneDrive().exists())
                                constantsInstance.getTmpFileDLFromOneDrive().delete();

                            //We disable the upload button and update the file status
                            //uploadButton.setEnabled(false);
                            updateStatusDocument(typeOfDoc,positionDocumentToUpload,idWorkSite,FileStatus.UPLOADED);
                            updateFilePathDocument(typeOfDoc,positionDocumentToUpload,null,false,idWorkSite);
                            ///updateFileStatusTextView(R.string.status_choosed_file_UPLOADED);
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
        }else {//No document has been chosen
            Toast.makeText(getApplicationContext(), "Veuillez choisir un document",Toast.LENGTH_LONG).show();
        }
    }

    public String getIdWorkSite() {
        return idWorkSite;
    }

    public void setPositionChoosedDocument(int positionChoosedDocument) {
        this.positionChoosedDocument = positionChoosedDocument;
    }

    public void setTypeOfChoosedDocument(int typeOfChoosedDocument) {
        this.typeOfChoosedDocument = typeOfChoosedDocument;
    }

    private void updateStatusDocument(DocumentTypes type, int position, String idWorkSite, FileStatus status){

        DocumentsManager.updateStatusDocument(type,position,idWorkSite,status);
        refreshFragmentByType(type);
    }

    private void updateNameChooseFileDocument(DocumentTypes type, int position, String idWorkSite, String chooseFile){

        DocumentsManager.updateNameChooseFileDocument(type,position,idWorkSite,chooseFile);
        refreshFragmentByType(type);
    }

    private String updateFilePathDocument(DocumentTypes type, int position, Uri filePath, boolean contentResolverNeeded,String idWorksite){ //return the name of the document thanks to its URI

        if(contentResolverNeeded){
            String res= DocumentsManager.updateFilePathDocument(type,position,idWorksite,filePath,getContentResolver());
            refreshFragmentByType(type);
            return res;
        }else {
            DocumentsManager.updateFilePathDocument(type,position,idWorksite,filePath);
            refreshFragmentByType(type);
        }
        return null;
    }

    private void refreshFragmentByType(DocumentTypes type) {

        switch (type){
            case OTHER_DOCUMENTS:
                refreshOtherDocumentFragment();

                break;
            case PLAN_DOCUMENTS:
                refreshPlanDocumentFragment();
                break;
            case SECURITY_DOCUMENTS:
                refreshSecurityDocumentFragment();
                break;
            case PPSPS_DOCUMENTS:
                refreshSecurityDocumentFragment();
                break;
            default:;
        }
    }

    private void updateNameUploadedFileDocument(DocumentTypes type, int position, String nameFile,String idWorksite){

        DocumentsManager.updateNameUploadedFileDocument(type,position,nameFile,idWorksite);
        refreshFragmentByType(type);
    }

    private Document getDocumentByPositionAndType(DocumentTypes type, int position,String idWorksite){
        Document res=null;
        res=DocumentsManager.getDocumentByPositionAndType(type,position,idWorksite);
        refreshFragmentByType(type);
        return res;
    }

    public void setOtherDocumentsList(ArrayList<Document> otherDocumentsList) {
        this.otherDocumentsList = otherDocumentsList;
    }

    public void setSecurityDocumentsList(ArrayList<Document> securityDocumentsList) {
        this.securityDocumentsList = securityDocumentsList;
    }

    public void setPpspsDocumentsList(ArrayList<Document> ppspsDocumentsList) {
        this.ppspsDocumentsList = ppspsDocumentsList;
    }

    public void setPlanDocumentsList(ArrayList<Document> planDocumentsList) {
        this.planDocumentsList = planDocumentsList;
    }

    public void refreshOtherDocumentFragment(){

       if(otherDocumentsFragment.getAdapter()!=null){
           otherDocumentsFragment.getAdapter().notifyDataSetChanged();
           Log.i(TAG,"other security called");
           Log.i(TAG,"adapter secu "+securityDocumentsFragment.getAdapter());

       }

    }

    public void refreshSecurityDocumentFragment(){
        if(securityDocumentsFragment.getAdapter()!=null){
            securityDocumentsFragment.getAdapter().notifyDataSetChanged();
            Log.i(TAG,"notify security called");
            Log.i(TAG,"adapter secu "+securityDocumentsFragment.getAdapter());
        }
    }

    public void refreshPlanDocumentFragment(){
        if(planDocumentsFragment.getAdapter()!=null){
            planDocumentsFragment.getAdapter().notifyDataSetChanged();
            Log.i(TAG,"plan security called");
            Log.i(TAG,"adapter secu "+securityDocumentsFragment.getAdapter());
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
