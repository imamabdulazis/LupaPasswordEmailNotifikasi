package com.imamabdulazis.loginfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPasswordActivity extends AppCompatActivity {
    private String TAG=getClass().getSimpleName();
    private EditText etemailkonfirmasi;
    private Button btlupakon;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
        etemailkonfirmasi = findViewById(R.id.etemailkonfirmasi);
        btlupakon= findViewById(R.id.btlupakonfirmasi);
        firebaseAuth=FirebaseAuth.getInstance();

        btlupakon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String useremail = etemailkonfirmasi.getText().toString().trim();

                if (TextUtils.isEmpty(useremail)){
                    Toast.makeText(LupaPasswordActivity.this, "Silahkan Masukkan Email !!", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                               // progressDialog.dismiss();
                                Toast.makeText(LupaPasswordActivity.this, "Silahkan Cek Email untuk reset password", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "Email: "+useremail);
                            }else{
                                //progressDialog.dismiss();
                                Toast.makeText(LupaPasswordActivity.this, "Error saat mengirim ke email, harap coba kembali", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "Email: "+useremail);
                            }
                        }
                    });
                }
            }
        });
    }
}
