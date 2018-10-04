package com.tunex.myprivatecontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addContact(View view){

        Intent intent = new Intent(this, NewContactActivity.class);
        startActivity(intent);
    }

    public void viewContact(View view){

        Intent intent = new Intent(this, DataListActivity.class);
        startActivity(intent);
    }

    public void searchContact(View view){

        Intent intent = new Intent(this, SearchContactActivity.class);
        startActivity(intent);

    }

    public void updateContact(View view){

        Intent intent = new Intent(this, UpdateContactActivity.class);
        startActivity(intent);

    }
}
