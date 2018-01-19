package com.example.inderpreet.ebook_junction;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by inderpreet on 18/10/17.
 */

public class webpage extends search {
    String ShowOrHideWebViewInitialUse = "show";
    public static String weburl = "";
    private ProgressBar spinner;
    public WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webview = (WebView)findViewById(R.id.webview);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new CustomWebViewClient());
        webview.loadUrl("http://gen.lib.rus.ec/search.php?req="+text+"&open=0&res=100");
    }
    private class CustomWebViewClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String loo) {
            if (loo.startsWith("http://libgen.io")) {
                weburl = loo;
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                Intent x = new Intent(getApplicationContext(), new_download.class);
                startActivity(x);
                return true;
            }
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest ooo) {
            if (Uri.parse(ooo.getUrl().toString()).getHost().startsWith("libgen.io")) {
                weburl = ooo.getUrl().toString();
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                Intent x = new Intent(getApplicationContext(), new_download.class);
                startActivity(x);
                return true;
            }
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
            webview.setVisibility(webview.INVISIBLE);
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementsByTagName('ul')[0].style.display='none'; " +
                    "document.getElementsByTagName('table')[0].style.display='none'; " +
                    " var x; " +
                    " var y; " +
                    " var i; " +
                    " var j; " +
                    " var g; " +
                    " var r; " +
                    " var k; " +
                    "if (document.getElementById('paginator_example_top') == null ){" +
                    "document.getElementsByTagName('table')[1].style.display='none'; " +

                    " y = document.getElementsByTagName('tr')[2] ; " +
                    " x = document.getElementsByTagName('tr')[3] ; " +
                    "g = document.getElementsByTagName('tr')[2].cells.length ;" +
                    "y.getElementsByTagName('td')[g-2].innerHTML='Link'; " +
                    "y.getElementsByTagName('td')[g-2].setAttribute('style', 'font-weight:bold'); " +
                    "y.getElementsByTagName('td')[g-1].style.display='none'; " +
                    "  i = 3 ; }" +

                    "else{" +
                    "document.getElementsByTagName('table')[1].style.display='none'; " +
                    "document.getElementsByTagName('table')[2].style.display='none'; " +
                    "document.getElementsByTagName('table')[4].style.display='none'; " +
                    "document.getElementsByTagName('table')[5].style.display='none'; " +

                    " y = document.getElementsByTagName('tr')[4] ; " +
                    " x = document.getElementsByTagName('tr')[5] ; " +
                    "g = document.getElementsByTagName('tr')[4].cells.length ;" +
                    "y.getElementsByTagName('td')[g-2].innerHTML='Link'; " +
                    "y.getElementsByTagName('td')[g-2].setAttribute('style', 'font-weight:bold'); " +
                    "y.getElementsByTagName('td')[g-1].style.display='none'; " +
                    " i = 5 ; }" +

                    " var q = y.getElementsByTagName('a').length   ; " +
                    " y.getElementsByTagName('td')[0].style.display='none' ;" +
                    "for(j=0;j<q;j++){ " +
                    " y.getElementsByTagName('a')[j].removeAttribute('href'); }" +

                    "while ( x != document.getElementById('paginator_example_bottom') ){" +
                    " var p = x.getElementsByTagName('a').length   ; " +
                    " x.getElementsByTagName('td')[0].style.display='none' ;" +
                    " r = x.getElementsByTagName('td')[2].getElementsByTagName('a').length ;" +
                    " k = x.getElementsByTagName('td')[2].getElementsByTagName('a')[r-1].getElementsByTagName('i').length ;" +
                    " if(k!=0){ "  +
                    " x.getElementsByTagName('td')[2].getElementsByTagName('a')[r-1].getElementsByTagName('i')[k-1].style.display='none' ;}" +
                    "for(j=0;j<p;j++){ " +

                    " if( j == p-5 ) { " +
                    "g = x.cells.length ;" +
                    "x.getElementsByTagName('td')[g-5].getElementsByTagName('a')[0].innerHTML='DOWNLOAD'; " +
                    " continue ; }" +
                    " if( j < p-5 ) { " +
                    " x.getElementsByTagName('a')[j].removeAttribute('href');  }" +

                    " if( j == p-5 ) { " +
                    " x.getElementsByTagName('a')[j].style.display='none'; } " +

                    " else if( j > p-5 ) { " +
                    " x.getElementsByTagName('a')[j].style.display='none'; } }" +
                    " i++ ; " +
                    " x = document.getElementsByTagName('tr')[i] ; }" +
                    "})()");
            spinner.setVisibility(View.VISIBLE);
            webview.setVisibility(webview.INVISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    spinner.setVisibility(View.GONE);
                    webview.setVisibility(webview.VISIBLE);
                    ShowOrHideWebViewInitialUse = "hide";
                }
            }, 1500);

            super.onPageFinished(view, url);



        }
    }
}

