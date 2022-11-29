package com.akshaj02.shopnsplit;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class change_password extends AppCompatActivity {

    EditText oldPassword, newPassword;
    EditText confirmPassword;
    FirebaseAuth firebaseAuth;
    Button cancel;
    Button changePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        oldPassword = findViewById(R.id.old_pass);
        newPassword = findViewById(R.id.new_pass);
        confirmPassword = findViewById(R.id.confirmed_new_pass);
        cancel = findViewById(R.id.cancel);
        changePassword = findViewById(R.id.passchange);

        firebaseAuth = FirebaseAuth.getInstance();


        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(change_password.this, Settings.class);
                startActivity(intent);
            }
        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = oldPassword.getText().toString();
                String newPass = newPassword.getText().toString();
                String confirmPass = confirmPassword.getText().toString();

                if (oldPass.isEmpty()) {
                    oldPassword.setError("Please enter your old password");
                    oldPassword.requestFocus();
                } else if (newPass.isEmpty()) {
                    newPassword.setError("Please enter your new password");
                    newPassword.requestFocus();
                } else if (confirmPass.isEmpty()) {
                    confirmPassword.setError("Please confirm your new password");
                    confirmPassword.requestFocus();
                } else if (!newPass.equals(confirmPass)) {
                    confirmPassword.setError("Passwords do not match");
                    confirmPassword.requestFocus();
                } else if (oldPass.equals(newPass)) {
                    newPassword.setError("New password cannot be the same as old password");
                    newPassword.requestFocus();
                } else {
                    firebaseAuth.getCurrentUser().updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(change_password.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(change_password.this, Settings.class));
                            } else {
                                Toast.makeText(change_password.this, "Password change failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

