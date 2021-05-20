package com.example.ivg_version_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Expense extends AppCompatActivity {


    EditText Item, Price, Quantity;
    TextView Total;
    ListView Item_name, Price_list, Quantity_list;

     ArrayAdapter<String> adapter;
     ArrayAdapter<Integer> Adapter ;
     ArrayAdapter <Integer> dataAdapter;

     ArrayList<String> List_Item;
     ArrayList<Integer> List_price ;
     ArrayList<Integer> List_qty ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Item = (EditText)findViewById(R.id.Item);
        Price = (EditText)findViewById(R.id.Rupee);
        Quantity = (EditText)findViewById(R.id.quantity);
        Total = (TextView)findViewById(R.id.Total);

         Item_name = (ListView)findViewById(R.id.List_view_item);
         Price_list = (ListView)findViewById(R.id.List_view_price);
         Quantity_list = (ListView)findViewById(R.id.List_view_quantity);










    }

    public void onSaveClickL(View view) {








    }

    public void onadditemClick(View view) {



        List_Item= new ArrayList<>();
        List_price = new ArrayList<>();
        List_qty = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List_Item);
        Adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,List_price);
        dataAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,List_qty);

        Price_list.setAdapter(Adapter);
        Item_name.setAdapter(adapter);
        Quantity_list.setAdapter(dataAdapter);


        String Item_1 = Item.getText().toString();
        String Price_1 = Price.getText().toString();
        if (TextUtils.isEmpty(Item_1) | TextUtils.isEmpty(Price_1)) {

            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT);
            return;
        } else {
            adapter.add(Item.getText().toString());
            Adapter.add(Integer.valueOf(Price.getText().toString()));
            dataAdapter.add(Integer.valueOf(Quantity.getText().toString()));


            int i;
            int sum = 0;

            for (i = 0; i < Adapter.getCount(); i++) {
                int val = Adapter.getItem(i) * dataAdapter.getItem(i);
                sum += val;

            }


            Adapter.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
            dataAdapter.notifyDataSetChanged();


            Price.setText("");
            Item.setText("");
            Quantity.setText("");
            String str = String.valueOf(sum);
            Total.setText(str);

        }

    }

    public void onClearClickL(View view) {
        int count = Adapter.getCount();
        adapter.remove(adapter.getItem(count-1));
        Adapter.remove(Adapter.getItem(count-1));
        dataAdapter.remove(dataAdapter.getItem(count-1));

        Adapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        dataAdapter.notifyDataSetChanged();
        Total.setText("");
    }

    public void onDeleteClick(View view) {
        Adapter.clear();
        adapter.clear();
        dataAdapter.clear();
        Price.setText("");
        Item.setText("");
        Quantity.setText("");
        Total.setText("");
    }
}
