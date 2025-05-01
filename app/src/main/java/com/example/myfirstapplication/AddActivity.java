package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    EditText account, user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        account = findViewById(R.id.edit_account);
        user = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getText().toString().isEmpty() ||
                        user.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty()
                ) {
                    Toast.makeText(
                            getApplicationContext(),
                            "You need to fill out EVERYTHING!",
                            Toast.LENGTH_LONG).show();
                } else {
                    addAccount();
                }

            }
        });
    }

    private void addAccount() {
        Account newAccount = new Account(
                account.getText().toString(),
                user.getText().toString(),
                password.getText().toString());

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("Account", newAccount);
        startActivity(intent);
    }
}