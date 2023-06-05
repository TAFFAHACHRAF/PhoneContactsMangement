package com.example.contact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.contact.controller.ContactService;
import com.example.contact.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private RecyclerView listContacts;
    private AdapterContact adapterContact;
    private Button addContact;
    String url = "http://192.168.0.142:8000";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContacts = findViewById(R.id.contactRv);
        btnAdd = findViewById(R.id.fablab);
        listContacts.setHasFixedSize(true);
        addContact = findViewById(R.id.add_contact);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add new contact", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,AddContact.class);
                startActivity(i);
            }
        });

        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == Activity.RESULT_OK) {
            if (data != null && data.getBooleanExtra("data_updated", false)) {
                loadData();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    public  void loadData(){
        Retrofit retrofit = new Retrofit.Builder( ).baseUrl(url).addConverterFactory(GsonConverterFactory.create( )).build( );
        ContactService api = retrofit.create(ContactService.class);

        Call<List<Contact>> call = api.getAllContacts();
        call.enqueue(new Callback<List<Contact>>( ) {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> data = response.body( );
                adapterContact = new AdapterContact(MainActivity.this, Listin(data));
                listContacts.setAdapter(adapterContact);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }

    ArrayList Listin(List<Contact> l) {
        ArrayList<Contact> maliste = new ArrayList<>( );
        for (int i = 0; i < l.size( ); i++) {
            Contact c=new Contact(l.get(i));
            maliste.add(c);
        }
        return maliste;
    }
}





