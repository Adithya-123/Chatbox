package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class chat extends AppCompatActivity {

    SearchView listview;
    ListView mylist;
    ImageButton imageButton;
    EditText editText;
    Button send;
    EditText phone;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listview = findViewById(R.id.listview);
        mylist = findViewById(R.id.scroll);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.button);
        phone = findViewById(R.id.phone);
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chat.this, chat.class);
                startActivity(intent);
            }
        });
        list = new ArrayList<String>();
        list.add("Aditya");
        list.add("Aditya1");
        list.add("Aditya2");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        mylist.setAdapter(adapter);

        listview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    public void btn_send(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

            MyMessage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);

        }
    }

    private void MyMessage() {
        String phoneNumber = phone.getText().toString().trim();
        String Message = editText.getText().toString().trim();

        if (!phone.getText().toString().equals("") || !editText.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Message, null, null);
            Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Please enter number or message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:

            if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                MyMessage();

            }
            else {
                Toast.makeText(this,"you  dont have required pression to make this action",Toast.LENGTH_SHORT).show();
            }
                break;

        }

    }
}

