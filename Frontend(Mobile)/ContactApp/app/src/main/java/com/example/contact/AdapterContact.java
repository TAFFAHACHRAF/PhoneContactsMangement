package com.example.contact;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ContactViewHolder> {
    private Context context;
    private ArrayList<Contact> contactList;
    String url = "http://192.168.43.250:8000";

    public AdapterContact(Context context, ArrayList<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_contact_item,parent,false);
        ContactViewHolder vh = new ContactViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact modelContact = contactList.get(position);

        final long id = modelContact.getCONTACT_ID();
        final String firstName = modelContact.getCONTACT_FIRSTNAME();
        String lastName = modelContact.getCONTACT_LASTNAME();

        holder.contactName.setText(firstName +" "+ lastName);

        holder.contactImage.setImageResource(R.drawable.ic_baseline_person_24);

        holder.contactDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri telephone = Uri.parse("tel:" + modelContact.getCONTACT_PHONE());
                Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
                context.startActivity(secondeActivite);
            }
        });



    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        ImageView contactImage,contactDial;
        TextView contactName;
        RelativeLayout relativeLayout;
        FloatingActionButton fab;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            //init view
            contactImage = itemView.findViewById(R.id.contact_image);
            contactDial = itemView.findViewById(R.id.contact_number_dial);
            contactName = itemView.findViewById(R.id.contact_name);
            //fab = itemView.findViewById(R.id.fablab);
            relativeLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
