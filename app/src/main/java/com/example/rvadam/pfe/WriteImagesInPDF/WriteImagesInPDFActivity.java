package com.example.rvadam.pfe.WriteImagesInPDF;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rvadam.pfe.R;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteImagesInPDFActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_images_in_pdf);
    }


        public void printDocument(View view)
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


                canvas.drawCircle(pageInfo.getPageWidth()/2,
                        pageInfo.getPageHeight()/2,
                        150,
                        paint);
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


