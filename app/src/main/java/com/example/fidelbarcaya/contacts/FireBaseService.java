package com.example.fidelbarcaya.contacts;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by fidel.barcaya on 7/10/2017.
 */

public class FireBaseService {


    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public FireBaseService(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("/");
    }

    public void addContact(Contact contact)
    {

        myRef.push().setValue(contact);
    }

    public void editContact(Contact contact)
    {
        myRef.child(contact.getId()).setValue(contact);
    }

    public void deleteContact(Contact contact)
    {
        myRef.child(contact.getId()).removeValue();

    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public DatabaseReference getMyRef() {
        return myRef;
    }

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }

}
