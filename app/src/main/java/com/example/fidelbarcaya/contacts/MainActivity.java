package com.example.fidelbarcaya.contacts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private static final String  TAG = "Contact";
    private RecyclerView recyclerView;
    private ContactListAdapter contactListAdapter;
    FireBaseService fireBaseService;
    Dialog contactForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactForm = new Dialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        contactListAdapter = new ContactListAdapter(this);

        recyclerView.setAdapter(contactListAdapter );
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactForm.buildForm("CREATE", null);
            }
        });


        fireBaseService = new FireBaseService();
        fireBaseService.getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    ArrayList<Contact> contacts = new ArrayList<Contact>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Contact contact = snapshot.getValue(Contact.class);
                        contact.setId(snapshot.getKey());
                        contacts.add(contact);
                    }
                    contactListAdapter.addContactList(contacts);
                }catch (Exception e)
                {
                    Log.d(TAG, "Error is: " + e.getMessage());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
