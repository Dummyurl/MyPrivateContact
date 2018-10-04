package com.tunex.myprivatecontact;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateContactActivity extends AppCompatActivity {

    TextView text_title;
    EditText Name_Search, New_Name, New_Mobile, New_Email;

    String SearchName, NewName, NewMobile, NewEmail;

    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    Button UpdateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact_layout);

        Name_Search = (EditText) findViewById(R.id.name_search);
        New_Name = (EditText) findViewById(R.id.new_name);
        New_Mobile = (EditText) findViewById(R.id.new_mobile);
        New_Email = (EditText) findViewById(R.id.new_email);

        UpdateButton =(Button) findViewById(R.id.update_button);

        text_title = (TextView) findViewById(R.id.title_text);

        New_Name.setVisibility(View.GONE);
        New_Mobile.setVisibility(View.GONE);
        New_Email.setVisibility(View.GONE);
    }

    public void searchContact(View view){

        SearchName = Name_Search.getText().toString();
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getContact(SearchName, sqLiteDatabase);

        if(cursor.moveToFirst()){

            NewMobile = cursor.getString(0);
            NewEmail = cursor.getString(1);
            NewName = SearchName;
            New_Name.setText(NewName);
            New_Mobile.setText(NewMobile);
            New_Email.setText(NewEmail);
            New_Name.setVisibility(View.VISIBLE);
            New_Mobile.setVisibility(View.VISIBLE);
            New_Email.setVisibility(View.VISIBLE);
            UpdateButton.setVisibility(View.VISIBLE);
            text_title.setVisibility(View.VISIBLE);

        }


    }

    public void updateContact(View view){

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getWritableDatabase();

        String name, mobile, email;

        name = New_Name.getText().toString();
        mobile = New_Mobile.getText().toString();
        email = New_Email.getText().toString();

        int count = userDbHelper.updateInformation(SearchName,name,mobile,email,sqLiteDatabase);

        Toast.makeText(getApplicationContext(), count + "contact updated", Toast.LENGTH_LONG).show();

    }
}
