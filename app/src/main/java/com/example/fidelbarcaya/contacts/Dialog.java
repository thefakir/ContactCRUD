package com.example.fidelbarcaya.contacts;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by fidel.barcaya on 7/10/2017.
 */

public class Dialog {

    private EditText nameField;
    private EditText addressField;
    private EditText birthDateField;
    private EditText phoneNumberField;
    private EditText emailField;
    private LayoutInflater inflater;
    private View subView;
    private AlertDialog.Builder builder;
    private Context context;
    private FireBaseService fireBaseService;
    final String CREATE = "CREATE";
    final String EDIT = "EDIT";

    public Dialog(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        subView = inflater.inflate(R.layout.form_contact, null);
        nameField = (EditText) subView.findViewById(R.id.enter_name);
        addressField = (EditText) subView.findViewById(R.id.enter_address);
        birthDateField = (EditText) subView.findViewById(R.id.enter_birth_date);
        phoneNumberField = (EditText) subView.findViewById(R.id.enter_phone_number);
        emailField = (EditText) subView.findViewById(R.id.enter_email);
        builder = new AlertDialog.Builder(context);
        fireBaseService = new FireBaseService();
    }

    public void buildForm(final String formType, final Contact contact) {
        String title = "";
        String button = "";
        if (formType.equals(CREATE)) {
            title = "Add new Contact";
            button = "ADD CONTACT";
        }
        if (formType.equals(EDIT)) {
            title = "Edit contact";
            button = "EDIT CONTACT";

            if (contact != null) {
                nameField.setText(contact.getName());
                addressField.setText(contact.getAddress());
                birthDateField.setText(contact.getBirthDate());
                phoneNumberField.setText(contact.getPhoneNumber());
                emailField.setText(contact.getEmail());
            }
        }

        builder.setTitle(title);
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton(button, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                String name = nameField.getText().toString();
                String address = addressField.getText().toString();
                String birthDate = birthDateField.getText().toString();
                String phoneNumber = phoneNumberField.getText().toString();
                String email = emailField.getText().toString();
                Contact contactToDB = new Contact(name, address, birthDate, phoneNumber, email);

                if (formType.equals(CREATE)) {
                    fireBaseService.addContact(contactToDB);
                }else{
                    contactToDB.setId(contact.getId());
                    fireBaseService.editContact(contactToDB);
                }
                dialog.cancel();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}