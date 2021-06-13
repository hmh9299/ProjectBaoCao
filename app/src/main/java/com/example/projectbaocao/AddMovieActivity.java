package com.example.projectbaocao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddMovieActivity extends AppCompatActivity {


    private AppCompatButton btAdd;
    private EditText txtName, txtDescription,txtDatePicked;
    private RatingBar ratingBar;
    private Spinner spinnerType;

    private Movie movie;
    private SQLiteMovieHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        initView();
        database = new SQLiteMovieHelper(this);
        database.getAllMovie();

        txtDatePicked.setOnClickListener(v -> {
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int month =  (Calendar.getInstance().get(Calendar.MONTH));
            int m= month+1;
            int year = Calendar.getInstance().get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,(view, year1, month1, dayOfMonth1) -> {
                txtDatePicked.setText(dayOfMonth1+"/"+month1+"/"+year1);
            },year,m,day);
            datePickerDialog.show();
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String description = txtDescription.getText().toString();
                String date = txtDatePicked.getText().toString();
                int rating = (int) ratingBar.getRating();
                String type = spinnerType.getSelectedItem().toString();
                Movie movie = new Movie(name,type,description,date,rating);
                database.addMovie(movie);
                database.closeDB();
                finish();
            }
        });

    }

    private void initView() {
        btAdd = findViewById(R.id.inputbt_add);
        txtName = findViewById(R.id.input_name);
        txtDescription = findViewById(R.id.input_description);
        txtDatePicked = findViewById(R.id.input_date);
        ratingBar = findViewById(R.id.input_ratingbar);
        spinnerType=findViewById(R.id.input_type);
    }
}