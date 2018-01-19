package com.example.inderpreet.ebook_junction;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by inderpreet on 18/10/17.
 */

public class new_download extends webpage {
    public WebView wv;
    public static String haha = "disco";
    private ProgressBar spinner;
    String ShowOrHideWebViewInitialUse = "show";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ndownload_activity);
        wv = (WebView) findViewById(R.id.Download);
        spinner = (ProgressBar)findViewById(R.id.progressBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new webVIEWclient());
        wv.setVerticalScrollBarEnabled(false);
        wv.loadUrl(weburl);
        wv.setWebChromeClient(new GoogleClient());
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.clearCache(true);
        wv.clearHistory();
        wv.setDownloadListener(new DownloadListener() {

            @Override


            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimeType,
                                        long contentLength) {
                String a = url.substring(url.lastIndexOf('.') + 1).trim();
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));


                request.setMimeType(mimeType);


                String cookies = CookieManager.getInstance().getCookie(url);


                request.addRequestHeader("cookie", cookies);


                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");


                request.setTitle(URLUtil.guessFileName(url, contentDisposition,
                        mimeType));


                request.allowScanningByMediaScanner();


                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Toast.makeText(getApplicationContext(),haha+"."+a,Toast.LENGTH_LONG).show();
                request.setDestinationInExternalFilesDir(new_download.this,
                        Environment.DIRECTORY_DOWNLOADS, haha+"."+a);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File",
                        Toast.LENGTH_LONG).show();
            }
        });
        wv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.loadUrl(weburl);
            }
        });
    }

    private class webVIEWclient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;

        }

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(webview.INVISIBLE);
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            spinner.setVisibility(View.VISIBLE);
            wv.setVisibility(wv.INVISIBLE);
            wv.loadUrl("javascript:(function() { " +
                    "document.getElementsByTagName('td')[0].style.display='none'; " +
                    "document.getElementsByTagName('td')[2].style.display='none'; " +
                    "document.getElementsByTagName('td')[1].setAttribute('align', 'left'); " +
                    "var str;" +
                    "str=document.getElementsByTagName('td')[1].getElementsByTagName('h1')[0].innerHTML;" +
                    "var q = document.getElementsByTagName('td')[1].getElementsByTagName('p');"+
                    "document.getElementsByTagName('td')[1].getElementsByTagName('p')[q.length-1].style.display='none'; " +
                    "document.getElementsByTagName('td')[1].getElementsByTagName('ins')[0].style.display='none'; " +
                    "javascript:alert(str);" +
                    "})()");
            spinner.setVisibility(View.VISIBLE);
            wv.setVisibility(wv.INVISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    spinner.setVisibility(View.GONE);
                    wv.setVisibility(wv.VISIBLE);
                    ShowOrHideWebViewInitialUse = "hide";
                }
            }, 1000);
            super.onPageFinished(view, url);
        }
    }


    class GoogleClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

        }
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            haha = message;
            result.confirm();
            return true;
        }
    }

}
