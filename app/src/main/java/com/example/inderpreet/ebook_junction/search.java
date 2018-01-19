package com.example.inderpreet.ebook_junction;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.inderpreet.ebook_junction.R.id.webview;

public class search extends AppCompatActivity {
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.editText);
                text= et.getText().toString();
                text = text.replaceAll("\\s","+");
                text=text.toLowerCase();
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),webpage.class);
                startActivity(i);
            }
        });

    }

}
