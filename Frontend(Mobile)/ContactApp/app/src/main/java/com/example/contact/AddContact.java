package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contact.controller.ContactService;
import com.example.contact.entities.Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddContact extends AppCompatActivity {
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText phone;
    private EditText gender;
    private EditText type;
    private Button btnAddContact;
    private ContactService api;
    String url = "http://192.168.0.142:8000";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        firstname = findViewById(R.id.CONTACT_FIRSTNAME);
        lastname = findViewById(R.id.CONTACT_LASTNAME);
        email = findViewById(R.id.CONTACT_EMAIL);
        phone = findViewById(R.id.CONTACT_PHONE);
        gender = findViewById(R.id.CONTACT_GENDER);
        type = findViewById(R.id.CONTACT_TYPE);
        btnAddContact = findViewById(R.id.add_contact);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ContactService.class);

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact=new Contact(0,"oijeod","jkzned","zkejfn","zeijzoiejf","zjnfeziufh","zoiejfiozjef");
                /*contact.setCONTACT_ID(0);
                contact.setCONTACT_FIRSTNAME(firstname.getText().toString());
                contact.setCONTACT_LASTNAME(lastname.getText().toString());
                contact.setCONTACT_PHONE(phone.getText().toString());
                contact.setCONTACT_PHONE(email.getText().toString());
                contact.setCONTACT_TYPE(type.getText().toString());
                contact.setCONTACT_GENDER(gender.getText().toString());*/

                /*Toast.makeText(MainActivity.this, "Add new contact", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),AddContact.class);
                startActivity(i);*/

                api.createContact(contact).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("data_updated", true);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Inserted Successfully.... "+firstname.getText().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        System.out.println("error404");
                        Toast.makeText(getApplicationContext(), "Error.... "+firstname.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}





