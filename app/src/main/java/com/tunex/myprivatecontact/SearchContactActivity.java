package com.tunex.myprivatecontact;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContactActivity extends AppCompatActivity {

    TextView Display_Email, Display_Mobile;
    EditText Search_Name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact_layout);

        Search_Name = (EditText) findViewById(R.id.search_name);
        Display_Email = (TextView) findViewById(R.id.display_email);
        Display_Mobile = (TextView) findViewById(R.id.display_mobile);

        Display_Email.setVisibility(View.GONE);
        Display_Mobile.setVisibility(View.GONE);
    }

    public void searchContact(View view){

        search_name = Search_Name.getText().toString();

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();

        Cursor cursor = userDbHelper.getContact(search_name,sqLiteDatabase);

        if(cursor.moveToFirst()){

            String MOBILE = cursor.getString(0);
            String EMAIL = cursor.getString(1);

            Display_Mobile.setText(MOBILE);
            Display_Email.setText(EMAIL);

            Display_Email.setVisibility(View.VISIBLE);
            Display_Mobile.setVisibility(View.VISIBLE);

        }


    }

    public void deleteContact(View view){

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();

        userDbHelper.deleteInformation(search_name,sqLiteDatabase);

        Toast.makeText(getApplicationContext(),"Contact deleted", Toast.LENGTH_LONG).show();

    }
}
