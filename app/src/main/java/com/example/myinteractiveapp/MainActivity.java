package com.example.myinteractiveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myinteractiveapp.DetailActivity;
import com.example.myinteractiveapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usr);
        password = findViewById(R.id.Pword);
        login = findViewById(R.id.login);
        spinner = findViewById(R.id.role);

        final ArrayList<String> role = new ArrayList<>();
        role.add("Administrator");
        role.add("Normal User");
        role.add("Manager");
        role.add("Executive Director");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,role);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String role =spinner.getSelectedItem().toString();
                SharedPreferences pref = getSharedPreferences("UserData",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("role",role);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fill all the fields",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences pref = getSharedPreferences("MyValues", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username",username.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.putString("role",spinner.toString());
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Data saved Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
