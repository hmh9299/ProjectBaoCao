package com.example.projectbaocao;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class DramaWebFragment extends Fragment {

    private AppCompatButton button1,button2,btLogout;
    public DramaWebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_drama_web, container, false);
        WebView webView = v.findViewById(R.id.contenWebview);
        AppCompatButton bt1 = v.findViewById(R.id.btChoice1);
        AppCompatButton bt2 = v.findViewById(R.id.btChoice2);
        AppCompatButton bt3 = v.findViewById(R.id.btChoice3);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://mydramalist.com/shows/top_korean_dramas");
                Toast.makeText(getContext(), "Đang tải dữ liệu", Toast.LENGTH_LONG).show();

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://mydramalist.com/episode-calendar");
                Toast.makeText(getContext(), "Đang tải dữ liệu", Toast.LENGTH_LONG).show();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_LONG).show();
            }
        });
    return v;
    }
}