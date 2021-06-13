package com.example.projectbaocao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectbaocao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private Button btCancel, btRegister;
    private EditText email, pass;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();

        initView();

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = pass.getText().toString();

                if (e.isEmpty()) {
                    email.setError("Email không được trống");
                    return;
                }
                if (p.isEmpty()) {
                    pass.setError("Password không được trống");
                }
                if (p.length() < 6) {
                    pass.setError("Khong nho hon 6 ki tu");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(e, p).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Thanh cong!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }

    private void initView() {
        email = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPass);
        btCancel = findViewById(R.id.btCancel);
        btRegister = findViewById(R.id.btRegister);
    }
}