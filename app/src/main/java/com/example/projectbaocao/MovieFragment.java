package com.example.projectbaocao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.TimeFormat;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieFragment extends Fragment {
    private SQLiteMovieHelper db;
    private List<Movie> movieList;
    private MovieAdapter adapter;
    private ListView listView;

    private TextView textViewTime,search;
    private AppCompatButton buttonAdd,buttonEdit;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        listView = v.findViewById(R.id.listDS);
        movieList = new ArrayList<>();
        db = new SQLiteMovieHelper(getContext());
        movieList = db.getAllMovie();
        adapter = new MovieAdapter(getContext(),R.layout.movie_card,movieList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        textViewTime = v.findViewById(R.id.realtimeDS);
        textViewTime.setText("Thời gian đăng nhập:\n" + DateFormat.getDateTimeInstance().format(new Date()));

        buttonAdd = v.findViewById(R.id.btAddDS);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddMovieActivity.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });
        buttonEdit = v.findViewById(R.id.btEdit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EditMovieActivity.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });

//        buttonSearch = v.findViewById(R.id.btSearchDS);
        search = v.findViewById(R.id.searchDS);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SearchMovieActivity.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.swapItems(db.getAllMovie());
    }

}