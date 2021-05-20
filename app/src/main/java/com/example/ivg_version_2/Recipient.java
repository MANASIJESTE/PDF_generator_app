package com.example.ivg_version_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Recipient extends AppCompatActivity {

    EditText Name_R, Phno_R, email_R, addr_R;
    Button save_R , clear_R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        Name_R = (EditText)findViewById(R.id.Name_R);
        Phno_R = (EditText)findViewById(R.id.Phno_R);
        email_R = (EditText)findViewById(R.id.email_R);
        addr_R = (EditText)findViewById(R.id.addr_R);

        save_R  = (Button)findViewById(R.id.save_R);
        clear_R =(Button)findViewById(R.id.Clear_R);





    }


    public void onHomeClickR(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }

    public void onClearClickR(View view) {

        Name_R.setText("");
        Phno_R.setText("");
        email_R.setText("");
        addr_R.setText("");

    }

    public void onSaveClickR(View view) {

        global.Recipient = Name_R.getText().toString();
        global.phno_R= Phno_R.getText().toString();
        global.email_R = email_R.getText().toString();
        global.addr_R = addr_R.getText().toString();

        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();


    }
}
