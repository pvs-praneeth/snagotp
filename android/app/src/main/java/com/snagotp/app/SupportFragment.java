package com.snagotp.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SupportFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        
        webView = view.findViewById(R.id.support_webview);
        
        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        
        // Set WebViewClient to handle page loading
        webView.setWebViewClient(new WebViewClient());
        
        // Load the Buy Me a Coffee widget HTML
        String htmlContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>Support</title>" +
                "<style>" +
                "body { margin: 0; padding: 20px; font-family: Arial, sans-serif; }" +
                "h2 { color: #333; text-align: center; }" +
                "p { color: #666; text-align: center; margin-bottom: 30px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h2>Support the Developer</h2>" +
                "<p>If you like this app and want to support its development, consider buying me a coffee!</p>" +
                "<script data-name='BMC-Widget' data-cfasync='false' src='https://cdnjs.buymeacoffee.com/1.0.0/widget.prod.min.js' data-id='pvs.praneeth' data-description='Support me on Buy me a coffee!' data-message='' data-color='#FF813F' data-position='Right' data-x_margin='18' data-y_margin='18'></script>" +
                "</body>" +
                "</html>";
        
        webView.loadData(htmlContent, "text/html", "UTF-8");
        
        return view;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
    }
}
