package com.example.myfirstapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ShowActivity extends AppCompatActivity {

    TextView account, user, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        account = findViewById(R.id.txt_account);
        user = findViewById(R.id.txt_user);
        password = findViewById(R.id.txt_password);

        Account retrievedAccount = (Account)getIntent().getSerializableExtra("Account");

        if (retrievedAccount != null)
        {
            account.setText(retrievedAccount.getAccountName());
            user.setText(retrievedAccount.getUserName());
            password.setText(retrievedAccount.getPassword());
        }


    }
}