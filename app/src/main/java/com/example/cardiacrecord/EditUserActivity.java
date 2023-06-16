package com.example.cardiacrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EditUserActivity extends AppCompatActivity {

    private Button updateUserBtn,deleteUserBtn;
    private TextInputEditText userNameEdt, cmnt, systolic,diostolic, heart;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private String userID;
    private UserRVModal userRVModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line creating our database reference.


        // initializing all our variables.
        updateUserBtn=findViewById(R.id.idBtnUpdate);
        deleteUserBtn=findViewById(R.id.idBtnDelete);
        userNameEdt = findViewById(R.id.idName);
        cmnt = findViewById(R.id.idComment);
        systolic = findViewById(R.id.idsPressure);
        diostolic= findViewById(R.id.idDPressure);
        heart = findViewById(R.id.idHeart);

        loadingPB = findViewById(R.id.idPBLoading);


        userRVModal=getIntent().getParcelableExtra("user");
        if(userRVModal!=null){
            userNameEdt.setText(userRVModal.getUserName());
            cmnt.setText(userRVModal.getUserDesc());
            heart.setText(userRVModal.getUserheart());
            diostolic.setText(userRVModal.getUserdio());
            systolic.setText(userRVModal.getUsersys());
            userID=userRVModal.getUserId();
        }


        databaseReference = firebaseDatabase.getReference("Users").child(userID);


        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);

                String userName = userNameEdt.getText().toString();
                String userDesc = cmnt.getText().toString();
                String usersys = systolic.getText().toString();
                String userdio = diostolic.getText().toString();
                String userheart = heart.getText().toString();
                String userdate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                String usertime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

                Map<String,Object>map=new HashMap<>();
                map.put("userName",userName);
                map.put("userDesc",userDesc);
                map.put("usersys",usersys);
                map.put("userdio",userdio);
                map.put("userheart",userheart);
                map.put("userId",userID);
                map.put("userdate",userdate);
                map.put("usertime",usertime);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditUserActivity.this, "Updated...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditUserActivity.this, MainActivity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditUserActivity.this, "Failed to update...", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

    }

    private void deleteUser(){
        databaseReference.removeValue();
        Toast.makeText(this, "Data removed...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditUserActivity.this,MainActivity.class));
    }
}