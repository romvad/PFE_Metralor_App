package com.example.rvadam.pfe.WriteImagesInPDF;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.Toast;

import com.example.rvadam.pfe.FirebaseStorageHelper.FirebaseStoragePhotosHelpers;
import com.example.rvadam.pfe.Login.LoginContract;
import com.example.rvadam.pfe.Model.ListOfPhotosSingleton;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.GeneratedPDFDocumentSizes;
import com.example.rvadam.pfe.Utils.ListOfPhotosSingletonManager;
import com.example.rvadam.pfe.Utils.WorkSitesManager;
import com.example.rvadam.pfe.Utils.WriteImagesInPDFTools;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteImagesInPDFActivity extends Activity {
private Map<SpacePhoto,Bitmap> bitmaps;
private List<SpacePhoto> listMapKeys;
private String[] urlsArray;
private ProgressDialog progressDialog;
private static final String TAG = "WriteImagesInPDFAc";
private boolean isWholeUrlsDownloadsRetrieved=false;
private String idWorksite;
private String worksiteName;
private Button buttonPrintCoursesAccess;
private Button buttonPrintMaltAdductions;
private Button buttonPrintSecurity;
private Button buttonPrintTechnicalEquipments;
private Button buttonPrintGeneralView;
FirebaseStoragePhotosHelpers helper;
private int nbPhotosToPrint;

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public Map<SpacePhoto, Bitmap> getBitmaps() {
        return bitmaps;
    }

    public String[] getUrlsArray() {
        return urlsArray;
    }

    public List<SpacePhoto> getListMapKeys() {
        return listMapKeys;
    }

    public int getNbPhotosToPrint() {
        return nbPhotosToPrint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_images_in_pdf);

        bitmaps=new HashMap<SpacePhoto, Bitmap>();
        listMapKeys = new ArrayList<SpacePhoto>();

        //Hardcoded
        idWorksite="-LBw-rNjtmo9G70LUU2Z";
        WorkSite w = WorkSitesManager.getWorkSiteById(idWorksite);
        worksiteName = w.getName();




        //Instanciation of the FirebaseStoragePhotosHelper to set the download URLs in each SpacePhoto object in the ListOfPhotosSingleton
        helper=new FirebaseStoragePhotosHelpers(this);
        //Listeners of the print button per category
        buttonPrintCoursesAccess= (Button) findViewById(R.id.buttonPrintCoursesAccess);
        buttonPrintCoursesAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we reset the list of bitmaps and the local list of photos that have an image, to avoid printing photos of a another category for which the button has been clicked before
                bitmaps.clear();
                listMapKeys.clear();
                //determination of the number of photos
                nbPhotosToPrint=ListOfPhotosSingletonManager.getListOfPhotosByCategory(PhotoCategories.COURSES_ACCESS).size();
                helper.retrieveSpacePhotoUrlDownloadsByPhotoCategories(PhotoCategories.COURSES_ACCESS,idWorksite);
            }
        });

        buttonPrintMaltAdductions = (Button) findViewById(R.id.buttonPrintMaltAdductions);
        buttonPrintMaltAdductions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we reset the list of bitmaps and the local list of photos that have an image, to avoid printing photos of a another category for which the button has been clicked before
                bitmaps.clear();
                listMapKeys.clear();
                //determination of the number of photos
                nbPhotosToPrint=ListOfPhotosSingletonManager.getListOfPhotosByCategory(PhotoCategories.MALT_ADDUCTIONS).size();
                helper.retrieveSpacePhotoUrlDownloadsByPhotoCategories(PhotoCategories.MALT_ADDUCTIONS,idWorksite);
            }

            });
        buttonPrintSecurity = (Button) findViewById(R.id.buttonPrintSecurity);
        buttonPrintSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we reset the list of bitmaps and the local list of photos that have an image, to avoid printing photos of a another category for which the button has been clicked before
                bitmaps.clear();
                listMapKeys.clear();
                //determination of the number of photos
                nbPhotosToPrint=ListOfPhotosSingletonManager.getListOfPhotosByCategory(PhotoCategories.SECURITY).size();
                helper.retrieveSpacePhotoUrlDownloadsByPhotoCategories(PhotoCategories.SECURITY,idWorksite);
            }

        });

        buttonPrintTechnicalEquipments = (Button) findViewById(R.id.buttonPrintTechnicalEquipments);
        buttonPrintTechnicalEquipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we reset the list of bitmaps and the local list of photos that have an image, to avoid printing photos of a another category for which the button has been clicked before
                bitmaps.clear();
                listMapKeys.clear();
                //determination of the number of photos
                nbPhotosToPrint=ListOfPhotosSingletonManager.getListOfPhotosByCategory(PhotoCategories.TECHNICAL_EQUIPMENTS).size();
                helper.retrieveSpacePhotoUrlDownloadsByPhotoCategories(PhotoCategories.TECHNICAL_EQUIPMENTS,idWorksite);
            }

        });

        buttonPrintGeneralView = (Button) findViewById(R.id.buttonPrintGeneralView);
        buttonPrintGeneralView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we reset the list of bitmaps and the local list of photos that have an image, to avoid printing photos of a another category for which the button has been clicked before
                bitmaps.clear();
                listMapKeys.clear();
                //determination of the number of photos
                nbPhotosToPrint=ListOfPhotosSingletonManager.getListOfPhotosByCategory(PhotoCategories.GENERAL_VIEW_ACCESS).size();
                helper.retrieveSpacePhotoUrlDownloadsByPhotoCategories(PhotoCategories.GENERAL_VIEW_ACCESS,idWorksite);
            }

        });

    }

    WriteImagesInPDFActivity getActivity(){
        return this;
    }

    public void callImageRetriever(PhotoCategories category){

        List<SpacePhoto> photosOfCategory= ListOfPhotosSingletonManager.getListOfPhotosByCategory(category);
        //String[] urls= new String[photosOfCategory.size()];
        String[] url = new String[1];
        int nbOfRetrieverCall=0;
        for(SpacePhoto photo : photosOfCategory){
            if(photo.getDownloadURL()!=null){
                ImageRetriever ir= new ImageRetriever(this,category,nbOfRetrieverCall);
                //urls[counter]=photo.getDownloadURL();
                url[0] = photo.getDownloadURL();
                //In the keys of the hashmap bitmaps, we store each photo object (already uploaded) that matches each bitmap to organize the pdf document
                bitmaps.put(photo,null);
                listMapKeys.add(photo);
                ir.execute(url[0]);
                nbOfRetrieverCall++;
            }

        }
        progressDialog.dismiss();
        //ir.execute(urls);//Launch the ImageRetriever AsyncTask


    }


        public void printDocument(PhotoCategories category)
        {//First method executed

            PrintManager printManager = (PrintManager) this
                    .getSystemService(Context.PRINT_SERVICE);

            String jobName = this.getString(R.string.app_name) +
                    " Document";

            printManager.print(jobName, new MyPrintDocumentAdapter(this,category,worksiteName),
                    null);
        }

    public class MyPrintDocumentAdapter extends PrintDocumentAdapter
        {
            Context context;
            private int pageHeight;
            private int pageWidth;
            public PdfDocument myPdfDocument;
            public int totalpages = 0;
            private boolean isGeneralAccessPhotosPrinted=false;
            private String worksiteName;
            private PhotoCategories category;
            List<String> photoToWriteTypes;

            int nbTypesProcessed=0; //allows us to know what is the current type of photo that is written
            int nbPreviousDrawPhotoPageCallForTheSameType=0;//allows us to know how many photos are waiting to be write for the current type of photo


            public MyPrintDocumentAdapter(Context context, PhotoCategories category, String worksiteName)
            {
                this.context = context;
                this.worksiteName = worksiteName;
                if(category==PhotoCategories.GENERAL_VIEW_ACCESS){
                    this.totalpages = 4;
                    isGeneralAccessPhotosPrinted=true;
                } else {
                    this.photoToWriteTypes= ListOfPhotosSingletonManager.getTypesByCategory(category);
                    this.totalpages = WriteImagesInPDFTools.getTotalOfPagesByCategory(category);
                    this.category = category;
                }

            }

            private boolean pageInRange(PageRange[] pageRanges, int page)
            {
                for (int i = 0; i<pageRanges.length; i++)
                {
                    if ((page >= pageRanges[i].getStart()) &&
                            (page <= pageRanges[i].getEnd()))
                        return true;
                }
                return false;
            }

            private void drawFirstPage(PdfDocument.Page page){
                Canvas canvas = page.getCanvas();



                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                //Write header of the page
                paint.setTextSize(GeneratedPDFDocumentSizes.SIZE_TEXT_HEADER.getSize());
                canvas.drawText(
                        worksiteName,
                        GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize(),
                        GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize(),
                        paint);

                canvas.drawText(
                        Integer.toString(1),//page number 1
                        WriteImagesInPDFTools.calculateXPageNumberHeader(),
                        GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize(),
                        paint
                );

                //Write the title of the document

                    paint.setTextSize(GeneratedPDFDocumentSizes.SIZE_TITLE.getSize());
                    canvas.drawText(
                            category.getName(),
                            GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize(),
                            WriteImagesInPDFTools.calculateYTitle(pageHeight),
                            paint);

            }

           /* private void drawPage(PdfDocument.Page page,
                                  int pagenumber) {
                Canvas canvas = page.getCanvas();

                pagenumber++; // Make sure page numbers start at 1

                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                //Write header of the page
                paint.setTextSize(GeneratedPDFDocumentSizes.SIZE_TEXT_HEADER.getSize());
                canvas.drawText(
                        worksiteName,
                        GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize(),
                        GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize(),
                        paint);

                canvas.drawText(
                        Integer.toString(pagenumber),
                        WriteImagesInPDFTools.calculateXPageNumberHeader(),
                        GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize(),
                        paint
                );

                //Write the title of the document
                if(pagenumber==1){
                    paint.setTextSize(GeneratedPDFDocumentSizes.SIZE_TITLE.getSize());
                    canvas.drawText(
                            category.getName(),
                            GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize(),
                            WriteImagesInPDFTools.calculateYTitle(pageHeight),
                            paint);
                }

                //Write the images with their names, in their type section


               // int titleBaseLine = 72;
                //int leftMargin = 54;



                paint.setTextSize(40);
                canvas.drawText(
                        worksiteName + " Page " + pagenumber,
                        leftMargin,
                        titleBaseLine,
                        paint);

                paint.setTextSize(14);
                canvas.drawText("This is some test content to verify that custom document printing works", leftMargin, titleBaseLine + 35, paint);

                if (pagenumber % 2 == 0)
                    paint.setColor(Color.RED);
                else
                    paint.setColor(Color.GREEN);

                PdfDocument.PageInfo pageInfo = page.getInfo();

                //Test with images

                Paint p =new Paint();
                Rect rectDest= new Rect(leftMargin,0,100,100);
                canvas.drawBitmap(bitmap.get(0),null,rectDest,p);*/
                //canvas.drawBitmap(bitmap.get(0),0,10,p);

                /*canvas.drawCircle(pageInfo.getPageWidth()/2,
                        pageInfo.getPageHeight()/2,
                        150,
                        paint);*/
           // }


            public Bitmap getBitmapFromURL(String src) {
                try {
                    URL url = new URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    return myBitmap;
                } catch (IOException e) {
                    // Log exception
                    return null;
                }
            }



            @Override
            public void onWrite(final PageRange[] pageRanges,
                                final ParcelFileDescriptor destination,
                                final CancellationSignal
                                        cancellationSignal,
                                final WriteResultCallback callback) {


                //List<String> photoToWriteTypes= WriteImagesInPDFTools.getTypesOfPhotosToWrite(listMapKeys);
                for(String type : photoToWriteTypes){

                }

                for (int i = 0; i < totalpages; i++) {


                    if (pageInRange(pageRanges, i))
                    {
                        PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                                pageHeight, i).create();

                        PdfDocument.Page page =
                                myPdfDocument.startPage(newPage);

                        if (cancellationSignal.isCanceled()) {
                            callback.onWriteCancelled();
                            myPdfDocument.close();
                            myPdfDocument = null;
                            return;
                        }
                        if(i==0){
                            drawFirstPage(page);
                        }else{
                            //String currentType=photoToWriteTypes.get(nbTypesProcessed);
                            drawPhotoPage(page,i,nbTypesProcessed,nbPreviousDrawPhotoPageCallForTheSameType);
                        }
                        //drawPage(page, i);
                        myPdfDocument.finishPage(page);
                    }
                }

                try {
                    myPdfDocument.writeTo(new FileOutputStream(
                            destination.getFileDescriptor()));
                } catch (IOException e) {
                    callback.onWriteFailed(e.toString());
                    return;
                } finally {
                    myPdfDocument.close();
                    myPdfDocument = null;
                }

                callback.onWriteFinished(pageRanges);
            }

            private void drawPhotoPage(PdfDocument.Page page, int pagenumber, int nbTypesProcessed, int nbPreviousDrawPhotoPageCallForTheSameType) {
                //Determine the name of the current type of photo that is processed

                if(nbTypesProcessed<photoToWriteTypes.size()){
                    String currentType= photoToWriteTypes.get(nbTypesProcessed);

                List<SpacePhoto> allPhotosOfCurrentType= ListOfPhotosSingletonManager.getListOfPhotosByCategoryAndType(category,currentType);

                int nbPhotosRemainingToWrite= allPhotosOfCurrentType.size() - nbPreviousDrawPhotoPageCallForTheSameType*6; //If there are previous call of this function for the same type, each call has written 6 photos
                int nbWrittenPhotosInThisCall=0;

                Canvas canvas = page.getCanvas();

                pagenumber++; // Make sure page numbers start at 1

                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                //Write header of the page
                paint.setTextSize(GeneratedPDFDocumentSizes.SIZE_TEXT_HEADER.getSize());
                canvas.drawText(
                        worksiteName,
                        GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize(),
                        GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize(),
                        paint);

                canvas.drawText(
                        Integer.toString(pagenumber),
                        WriteImagesInPDFTools.calculateXPageNumberHeader(),
                        GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize(),
                        paint
                );

                paint.setTextSize(GeneratedPDFDocumentSizes.SIZE_TYPE.getSize());
                canvas.drawText(
                        currentType,
                        GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize(),
                        WriteImagesInPDFTools.calculateYType(),
                        paint
                );

                for (int i=0;i<nbPhotosRemainingToWrite;i++){
                    if(nbWrittenPhotosInThisCall<=6){
                        SpacePhoto photoWritten = allPhotosOfCurrentType.get(allPhotosOfCurrentType.size()-nbPhotosRemainingToWrite);
                        int numberRow = nbWrittenPhotosInThisCall /2;
                        int numberColumn = nbWrittenPhotosInThisCall %2;
                        paint.setTextSize(GeneratedPDFDocumentSizes.SIZE_PHOTO_NAME.getSize());
                        canvas.drawText(
                                photoWritten.getTitle(),
                                WriteImagesInPDFTools.calculateXColumnOfNamesAndPhotos(pageWidth,numberColumn),
                                WriteImagesInPDFTools.calculateYPhotoNamesRowOnPage(pageHeight,numberRow),
                                paint
                        );

                        //Define size and place of the image
                        Paint p =new Paint();
                        int xImage=WriteImagesInPDFTools.calculateXColumnOfNamesAndPhotos(pageWidth,numberColumn);
                        int yImage= WriteImagesInPDFTools.calculateYPhotoRowOnPage(pageHeight,numberRow);
                        Rect rectDest= new Rect(xImage,yImage,xImage+ WriteImagesInPDFTools.calculatedWidthPhoto(pageWidth),WriteImagesInPDFTools.calculateHeightPhoto(pageHeight));
                        if(listMapKeys.contains(photoWritten)){//the photo currently written has an image to draw
                           canvas.drawBitmap(bitmaps.get(photoWritten),null,rectDest,p);
                        }else {// The photo isn't uploaded, replaced with black rectangle
                            p.setColor(Color.BLACK);
                            canvas.drawRect(rectDest,p);
                        }

                        nbWrittenPhotosInThisCall++;

                    }else {
                        break;
                    }

                }
                nbPhotosRemainingToWrite -=nbWrittenPhotosInThisCall;

                if(nbPhotosRemainingToWrite==0){
                    nbTypesProcessed++;// A whole type of photos is now written, we now write photos from the next category
                }else {

                }
                }

            }

            @Override
            public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
                myPdfDocument = new PrintedPdfDocument(context, newAttributes);

                pageHeight =
                        newAttributes.getMediaSize().getHeightMils()/1000 * 72;
                pageWidth =
                        newAttributes.getMediaSize().getWidthMils()/1000 * 72;

                if (cancellationSignal.isCanceled() ) {
                    layoutResultCallback.onLayoutCancelled();
                    return;
                }

                if (totalpages > 0) {
                    PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                            .Builder("print_output.pdf")
                            .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                            .setPageCount(totalpages);

                    PrintDocumentInfo info = builder.build();
                    layoutResultCallback.onLayoutFinished(info, true);
                } else {
                    layoutResultCallback.onLayoutFailed("Page count is zero.");
                }
            }
        }

    }


