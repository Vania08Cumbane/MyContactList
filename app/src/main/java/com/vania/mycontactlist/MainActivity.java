package com.vania.mycontactlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editContactName;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editAddress;
    private Button buttonSave;
    private Button buttonContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editContactName = findViewById(R.id.editContactName);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        editAddress = findViewById(R.id.editAddress);
        buttonSave = findViewById(R.id.buttonSave);
        buttonContactList = findViewById(R.id.buttonContactList);

        buttonSave.setOnClickListener(v -> saveContact());

        buttonContactList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
            startActivity(intent);
        });
    }

    private void saveContact() {
        String name = editContactName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();
        String address = editAddress.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a contact name", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact contact = new Contact();
        contact.setContactName(name);
        contact.setPhoneNumber(phone);
        contact.setEmail(email);
        contact.setStreetAddress(address);

        ContactDBHelper dbHelper = new ContactDBHelper(this);
        boolean isSaved = dbHelper.insertContact(contact);

        if (isSaved) {
            Toast.makeText(this, "Contact saved to database!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Error saving contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editContactName.setText("");
        editPhone.setText("");
        editEmail.setText("");
        editAddress.setText("");
    }
}