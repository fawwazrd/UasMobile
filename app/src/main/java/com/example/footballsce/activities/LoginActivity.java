package com.example.footballsce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballsce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "Sign In";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtPass;
    private Button btnMasuk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.input_email);
        edtPass = findViewById(R.id.input_password);
        btnMasuk = findViewById(R.id.button_signin);

        btnMasuk.setOnClickListener(this);



        TextView create = findViewById(R.id.toSignup);
}
    private boolean validateForm() {
        boolean result = true;

        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("Required");
            edtEmail.requestFocus();
            result = false;
        }

        if (TextUtils.isEmpty(edtPass.getText().toString())) {
            edtPass.setError("Required");
            edtPass.requestFocus();
            result = false;
        } else {
            edtPass.setError(null);
        }
        return result;
    }
    private void signIn() {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInUser:onComplete: " + task.isSuccessful());

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful",
                                    Toast.LENGTH_SHORT).show();
                            SuccessfulLoginHandle();

                        }
                        else {
                            edtEmail.setError("User not found");
                            Toast.makeText(LoginActivity.this, "Check your username and password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void SuccessfulLoginHandle() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void keRegis(View view) {
        Intent i = new Intent(LoginActivity.this,RegisActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.button_signin) {
            signIn();
        }
    }
}
