package com.example.ivg_version_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.*;

import androidx.appcompat.app.AppCompatActivity;



public class Issuer extends AppCompatActivity {

    Button save , Clear;
    EditText Name_1, phno,email,addr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuer);

          save = (Button)findViewById(R.id.save);
          Clear = (Button)findViewById(R.id.Clear);
          Name_1 = (EditText)findViewById(R.id.Name);
          phno = (EditText)findViewById(R.id.Phno);
          email = (EditText)findViewById(R.id.email);
          addr = (EditText)findViewById(R.id.addr);
    }

    public void onClearClick(View view) {
        Name_1.setText("");
        phno.setText("");
        email.setText("");
        addr.setText("");
    }

    public void onSaveClick(View view) {



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        global.Issuer = Name_1.getText().toString();
        global.phno_I = phno.getText().toString();
        global.email_I = email.getText().toString();
        global.addr_I = addr.getText().toString();

        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();

    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
