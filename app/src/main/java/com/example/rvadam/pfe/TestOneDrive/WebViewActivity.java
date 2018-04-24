
package com.example.rvadam.pfe.TestOneDrive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.rvadam.pfe.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webview = new WebView(this);
        setContentView(webview);

        String supportWebViewUrl="http://docs.google.com/viewer?url=";
        String location="https://drive.google.com/open?id=0B77xq8MAflunRWlqOVh2WDh3RjA";
        String doc="<iframe src="+supportWebViewUrl+location+ "width=\"100%\" height=\"100%\" style=\"border: none;\"></iframe>";

        webview.getSettings().setJavaScriptEnabled(true);
        //webview.getSettings().setPluginsEnabled(true);
        //webview.getSettings().setAllowFileAccess(false);
        //webview.loadUrl(doc);
        //webview.loadData(doc, "text/html", null);
        webview.loadUrl("https://drive.google.com/open?id=0B77xq8MAflunRWlqOVh2WDh3RjA");
    }
}
