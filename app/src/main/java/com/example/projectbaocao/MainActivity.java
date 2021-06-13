package com.example.projectbaocao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieFragment movieFragment = new MovieFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainlayout,movieFragment,"");
        fragmentTransaction.commit();

        BottomNavigationView navigationView = findViewById(R.id.btmnavbar);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mListMovie:
                        MovieFragment fragment = new MovieFragment();
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.mainlayout,fragment,"");
                        fragmentTransaction1.commit();
                        return true;
                    case R.id.mNewsMovie:
                        NewsFragment testFragment = new NewsFragment();
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.mainlayout,testFragment,"");
                        fragmentTransaction2.commit();
                        return true;
                    case R.id.mInfo:
                        DramaWebFragment dramaWebFragment = new DramaWebFragment();
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.mainlayout, dramaWebFragment,"");
                        fragmentTransaction3.commit();
                        return true;
                }
                return false;
            }
        });
    }
}