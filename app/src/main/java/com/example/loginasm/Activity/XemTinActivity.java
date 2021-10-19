package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.loginasm.R;

public class XemTinActivity extends AppCompatActivity {
    WebView wv;
    String link;
    ProgressDialog progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_tin);
        wv=(WebView)findViewById(R.id.webView1);
        link=getIntent().getExtras().getString("link");
        wv.loadUrl(link);
        link=getIntent().getExtras().getString("link");
        progressbar=ProgressDialog.show(this, "cho chut", "loading.....");
        wv.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                if(progressbar.isShowing())
                    progressbar.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                wv.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);

            }

        });
        wv.loadUrl(link);
    }
}