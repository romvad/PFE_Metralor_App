package com.example.rvadam.pfe.TestOneDrive;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rvadam.pfe.R;
import com.microsoft.onedrivesdk.picker.IPicker;
import com.microsoft.onedrivesdk.picker.IPickerResult;
import com.microsoft.onedrivesdk.picker.LinkType;
import com.microsoft.onedrivesdk.picker.Picker;
import com.microsoft.onedrivesdk.saver.ISaver;
import com.microsoft.onedrivesdk.saver.Saver;
import com.microsoft.onedrivesdk.saver.SaverException;

import java.io.File;

public class TestOneDriveActivity extends AppCompatActivity {

    private IPicker mPicker;
    private String ONEDRIVE_Client_ID = "46b2631c-ec3e-4920-a5dd-6586e5733adf";
    private ISaver mSaver;
    private boolean picker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_one_drive);

        // Call the listener method to pick the image
        ((Button)findViewById(R.id.startPickerButton))
                .setOnClickListener(mStartPickingListener);

        // Call the listener method to upload the file to OneDrive
        ((Button)findViewById(R.id.saver)).setOnClickListener(FilesaverListener);
    }

    /**
     * The onClickListener that will start the OneDrive picker
     * */
    private final View.OnClickListener mStartPickingListener =
            new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    picker = true;
                    mPicker = Picker.createPicker(ONEDRIVE_Client_ID);
                    mPicker.startPicking((Activity)v.getContext(), LinkType.WebViewLink);
                }
            };

    /**
     * The onClickListener that will upload file to the OneDrive
     * */
    private final View.OnClickListener FilesaverListener = new
            View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    picker = false;

                    // create example file to save to OneDrive
                    final String filename = "";

                    // file path
                    File file = new File(Environment
                            .getExternalStorageDirectory() + "/Pictures/", filename);

                    Log.e("file", Uri.parse("file://" +
                    file.getAbsolutePath()) +"...." + file);

                    // create and launch the saver
                    mSaver = Saver.createSaver(ONEDRIVE_Client_ID);
                    mSaver.startSaving((Activity)v.getContext(),
                            filename,  Uri.parse("file://" + file.getAbsolutePath()));
                }
            };

    protected void onActivityResult(final int requestCode,
                                    final int resultCode, final Intent data) {

        if(picker == true) {

            // Get the results from the picker
            IPickerResult result = mPicker
                    .getPickerResult(requestCode, resultCode, data);

            // Handle the case if nothing was picked
            if (result != null) {

                // Do something with the picked file
                Log.e("main", "Link to file '" + result
                        .getName() + ": " + result.getLink());

                ((TextView) findViewById(R.id.pic_name))
                        .setText(result.getName());

                ((TextView) findViewById(R.id.pic_link))
                        .setText(result.getLink().toString());

                return;
            }
        } else {

            // check that the file was successfully saved to OneDrive
            try {

                mSaver.handleSave(requestCode, resultCode, data);

            } catch (final SaverException e) {

                // Log error information
                Log.e("OneDriveSaver",
                e.getErrorType()
                        .toString()); // Provides one of the SaverError enum
                // Log.d("OneDriveSaver",e.getDebugErrorInfo()); // Detailed debug error message
            }
        }

        // Handle non-OneDrive picker request
        super.onActivityResult(requestCode, resultCode, data);
    }
}
