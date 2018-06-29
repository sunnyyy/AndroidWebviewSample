package com.example.webview.androidwebviewsample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity  {

    private WebView webview;
    private static String testURL = "https://sunnyyy.github.io/demo/jsb/";
    private static String testJSBridge = "test_param:test_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.webView);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.loadUrl(testURL);

        webview.setWebViewClient(new MyBrowser());
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String thisURL = Uri.parse(url).toString();
            String[] parts = thisURL.split("/");
            String lastPartOfURL = parts[parts.length-1];
            Log.d("SUN","This is the last chunk of your URL : "+lastPartOfURL);
            if (lastPartOfURL.equals(testJSBridge)) {
                Log.e("SUN","Do not process the link you just clicked on like a normal URL.\nInstead, do something else with it, like open a specific intent.");
                return true;
            }
//            view.loadUrl(url);
            return false;
        }
    }
}