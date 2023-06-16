package com.example.cardiacrecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton addUserFAB;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private RecyclerView userRV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    private ArrayList<UserRVModal> userRVModalArrayList;
    private CourseRVAdapter courseRVAdapter;
    private RelativeLayout homeRL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}