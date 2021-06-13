package com.example.projectbaocao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class SearchMovieActivity extends AppCompatActivity {

    private ListView listView;
    private MovieAdapter adapter;
    private SQLiteMovieHelper database;
    private Context context;

    private RadioButton radioName,radioStar;
    private AppCompatButton buttonSearch;
    private EditText txtSearch;
    private TextView txtNumRes;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        context=this;
        initView();

        database = new SQLiteMovieHelper(getBaseContext());
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioName.isChecked()){
                    List<Movie> movies = database.searchName(txtSearch.getText().toString());
                    adapter = new MovieAdapter(getBaseContext(),R.layout.movie_card,movies);
                    listView.setAdapter(adapter);
                    num = Integer.parseInt(String.valueOf(movies.size()));
                    txtNumRes.setText("Số lượng kết quả: "+num);
                }
                if(radioStar.isChecked()){
                    List<Movie> movies = database.searchStar(Integer.parseInt(txtSearch.getText().toString()));
                    adapter = new MovieAdapter(getBaseContext(),R.layout.movie_card,movies);
                    listView.setAdapter(adapter);
                    num = Integer.parseInt(String.valueOf(movies.size()));
                    txtNumRes.setText("Số lượng kết quả: "+num);
                }
            }
        });
    }
    private void initView() {
        listView = findViewById(R.id.search_listview);
        buttonSearch = findViewById(R.id.search_btn);
        txtSearch = findViewById(R.id.search_name);
        radioName = findViewById(R.id.search_byName);
        radioStar = findViewById(R.id.search_byStar);
        txtNumRes = findViewById(R.id.search_resnum);
    }
}