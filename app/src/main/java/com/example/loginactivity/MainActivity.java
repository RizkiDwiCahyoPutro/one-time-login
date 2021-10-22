package com.example.loginactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtInfo;
    Button btnLogOut;

    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = findViewById(R.id.txtInfo);
        btnLogOut = findViewById(R.id.btnLogOut);

        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        CheckLogin();

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }

    public void CheckLogin() {
        if (shp == null)
            shp = getSharedPreferences("myPreferences", MODE_PRIVATE);


        String userName = shp.getString("name", "");

        if (userName != null && !userName.equals("")) {
            txtInfo.setText("Welcome " + userName);

        } else
        {
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
            finish();
        }
    }


    public void Logout() {
        try {
            if (shp == null)
                shp = getSharedPreferences("myPreferences", MODE_PRIVATE);

            shpEditor = shp.edit();
            shpEditor.putString("name", "");
            shpEditor.commit();

            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
            finish();

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}