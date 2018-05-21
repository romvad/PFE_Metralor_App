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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rvadam.pfe.R;
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
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WriteImagesInPDFActivity extends Activity {
private List<Bitmap> bitmap;
private  Button button;
private ProgressDialog progressDialog;
private static final String TAG = "WriteImagesInPDFAc";
private boolean isWholeUrlsDownloadsRetrieved=false;

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public Button getButton() {
        return button;
    }

    public List<Bitmap> getBitmap() {
        return bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_images_in_pdf);

        bitmap=new ArrayList<Bitmap>();
        button= (Button) findViewById(R.id.buttonPrint);
       // button.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference ref = FirebaseStorage.getInstance().getReference();
                StorageReference refPhoto=ref.child("-LBw-rNjtmo9G70LUU2Z/COURSES_ACCESS/MEANS_OF_ACCESS/boite_a_cles.jpg");
                Log.i(TAG,"refPhoto "+refPhoto);
                final String[] strArray = new String[1];
                refPhoto.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.i(TAG,"getDownloadUrl successfull");
                        strArray[0]=uri.toString();
                        isWholeUrlsDownloadsRetrieved=true;

                        if(isWholeUrlsDownloadsRetrieved){
                            ImageRetriever ir= new ImageRetriever(getActivity());
                            ir.execute(strArray);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Erreur de récupération de la photo depuis le cloud",Toast.LENGTH_LONG).show();
                    }
                });

                //String[] strArray= {"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2014/05/IMG_6424-1300x866.jpg"};


            }
        });


    }

    WriteImagesInPDFActivity getActivity(){
        return this;
    }


        public void printDocument()
        {//First method executed

            PrintManager printManager = (PrintManager) this
                    .getSystemService(Context.PRINT_SERVICE);

            String jobName = this.getString(R.string.app_name) +
                    " Document";

            printManager.print(jobName, new MyPrintDocumentAdapter(this),
                    null);
        }

        public class MyPrintDocumentAdapter extends PrintDocumentAdapter
        {
            Context context;
            private int pageHeight;
            private int pageWidth;
            public PdfDocument myPdfDocument;
            public int totalpages = 4;

            public MyPrintDocumentAdapter(Context context)
            {
                this.context = context;
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

            private void drawPage(PdfDocument.Page page,
                                  int pagenumber) {
                Canvas canvas = page.getCanvas();

                pagenumber++; // Make sure page numbers start at 1

                int titleBaseLine = 72;
                int leftMargin = 54;

                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setTextSize(40);
                canvas.drawText(
                        "Test Print Document Page " + pagenumber,
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
                canvas.drawBitmap(bitmap.get(0),null,rectDest,p);
                //canvas.drawBitmap(bitmap.get(0),0,10,p);

                /*canvas.drawCircle(pageInfo.getPageWidth()/2,
                        pageInfo.getPageHeight()/2,
                        150,
                        paint);*/
            }


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
                        drawPage(page, i);
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


