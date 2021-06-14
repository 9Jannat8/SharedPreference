package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private EditText userNameEditText, passwordEditText;
    private Button saveButton, loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.detailsTextId);
        userNameEditText = findViewById(R.id.usernameEditTextId);
        passwordEditText = findViewById(R.id.passwordEditTextId);
        saveButton = findViewById(R.id.saveButtonId);
        loadButton = findViewById(R.id.loadButtonId);

        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveButtonId)
        {
            String username = userNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.equals("") && password.equals(""))
            {
                Toast.makeText(getApplicationContext(), "please enter some data", Toast.LENGTH_SHORT).show();
            }

            else {
                //writing data
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey", username);
                editor.putString("userpasswordkey", password);
                editor.commit();
                userNameEditText.setText("");
                passwordEditText.setText("");
                Toast.makeText(getApplicationContext(), "Data is stored successfully", Toast.LENGTH_SHORT).show();
            }

        }

        else if(v.getId() == R.id.loadButtonId)
        {
            // to read data
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("userpasswordkey"))
            {
                String username = sharedPreferences.getString("usernameKey", "Data not found");
                String password = sharedPreferences.getString("userpasswordkey", "Data not found");
                textView.setText(username + "\n" + password);
            }
        }
    }
}