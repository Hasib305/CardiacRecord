package com.example.cardiacrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddUserActivity extends AppCompatActivity {

    // creating variables for our button, edit text,
    // firebase database, database reference, progress bar.
    private Button addUserBtn;
    private TextInputEditText userNameEdt, cmnt, systolic,diostolic, heart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        // initializing all our variables.
        addUserBtn = findViewById(R.id.idBtnAdd);
        userNameEdt = findViewById(R.id.idName);
        cmnt = findViewById(R.id.idComment);
        systolic = findViewById(R.id.idsPressure);
        diostolic= findViewById(R.id.idDPressure);
       heart = findViewById(R.id.idHeart);

        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line creating our database reference.
        databaseReference = firebaseDatabase.getReference("Users");
        // adding click listener for our add user button.
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our edit text.
                String userName = userNameEdt.getText().toString();
                String userDesc = cmnt.getText().toString();
                String usersys = systolic.getText().toString();
                String userdio = diostolic.getText().toString();
                String userheart = heart.getText().toString();
                String userdate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                String usertime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
                userID = userName;
                // on below line we are passing all data to our modal class.
                UserRVModal userRVModal = new UserRVModal( userName, userDesc, usersys,  userdio, userheart, userID,  userdate,  usertime) ;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(userID).setValue(userRVModal);
                        Toast.makeText(AddUserActivity.this, "User added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddUserActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddUserActivity.this, "Error is "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }


}