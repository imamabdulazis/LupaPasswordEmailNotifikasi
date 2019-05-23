package com.imamabdulazis.loginfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPasswordActivity extends AppCompatActivity {
    private EditText etemailkonfirmasi;
    private Button btlupakon;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
        etemailkonfirmasi = findViewById(R.id.etemailkonfirmasi);
        btlupakon= findViewById(R.id.btlupakonfirmasi);
        btlupakon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = etemailkonfirmasi.getText().toString().trim();

                if (useremail.equals("")){
                    Toast.makeText(LupaPasswordActivity.this, "Silahkan Masukkan Email Dancok !!!!", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LupaPasswordActivity.this, "Password Reset Email Telah Terkirim", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(LupaPasswordActivity.this,MainActivity.class));
                            }else{
                                Toast.makeText(LupaPasswordActivity.this, "Error saat mengirim ke email, harap coba kembali", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
