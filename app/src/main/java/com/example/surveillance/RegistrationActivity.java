package com.example.surveillance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {

    EditText mUsername,mEmail,mPassword;
    Button mRegister;
    Spinner mCity;
    FirebaseAuth fAuth;
    ProgressBar mProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mUsername=findViewById(R.id.username);
        mEmail=findViewById(R.id.email_reg);
        mPassword=findViewById(R.id.pass_reg);
        mRegister=findViewById(R.id.reg_button);
        mCity=findViewById(R.id.city);
        mProgress=findViewById(R.id.progressbar_reg);
        fAuth=FirebaseAuth.getInstance();



mRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email=mEmail.getText().toString().trim();
        String password=mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            mEmail.setError("Email is required");
        }

        if(TextUtils.isEmpty(password)){
            mPassword.setError("Password is required");
        }

        if(password.length()<6){
            mPassword.setError("Password length must be 6 ");
        }

        mProgress.setVisibility(View.VISIBLE);

    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                Toast.makeText(RegistrationActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                Animatoo.animateSlideLeft(RegistrationActivity.this);

            }
            else {
                Toast.makeText(RegistrationActivity.this,"Error occurred while registering user.Please try again.",Toast.LENGTH_SHORT).show();

            }
        }
    });






    }
});

        TextView signIn_text = findViewById(R.id.signIn_text);
        signIn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                Animatoo.animateSlideLeft(RegistrationActivity.this);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog ad = builder.create();
        ad.show();
    }
}