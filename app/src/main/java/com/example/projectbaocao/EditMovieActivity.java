package com.example.projectbaocao;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Calendar;

public class EditMovieActivity extends AppCompatActivity {

    private AppCompatButton btUpdate;
    private AppCompatButton btGet;
    private AppCompatButton btDelete;
    private EditText  txtID, txtName, txtDescription,txtDatePicked;
    private RatingBar ratingBar;

    private Movie movie;
    private SQLiteMovieHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        initView();

        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int year = Calendar.getInstance().get(Calendar.YEAR);

        txtDatePicked.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,(view, year1, month1, dayOfMonth) -> {
                txtDatePicked.setText(dayOfMonth+"/"+month1+"/"+year1);
            },year,month,day);
            datePickerDialog.show();
        });

        database = new SQLiteMovieHelper(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtID.getText().toString());
                String name = txtName.getText().toString();
                String description = txtDescription.getText().toString();
                String date = txtDatePicked.getText().toString();
                int rating = (int) ratingBar.getRating();
                Movie movie = new Movie(id,name,null,description,date,rating);
                database.updateMovie(movie);
                finish();
                database.getAllMovie();
            }
        });

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtID.getText().toString());
                Movie movie = database.getById(id);
                txtID.setText(String.valueOf(id));
                txtName.setText(movie.getName());
                txtDescription.setText(movie.getDescription());
                txtDatePicked.setText(movie.getDate());
                ratingBar.setRating(movie.getReview());
                txtID.setEnabled(false);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtID.getText().toString());
                database.deleteMovie(id);
                finish();
                database.getAllMovie();
            }
        });


    }

    private void initView() {
        btGet = findViewById(R.id.inputbt_get);
        btUpdate = findViewById(R.id.inputbt_update);
        btDelete = findViewById(R.id.inputbt_delete);
        txtID = findViewById(R.id.edit_id);
        txtName = findViewById(R.id.edit_name);
        txtDescription = findViewById(R.id.edit_description);
        txtDatePicked = findViewById(R.id.edit_date);
        ratingBar = findViewById(R.id.edit_ratingbar);
    }
}