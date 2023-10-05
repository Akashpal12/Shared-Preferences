package com.example.myapiaplication.RoomDatabaseFull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapiaplication.R;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {
    EditText edtName,edtAmount;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        edtAmount = findViewById(R.id.edtAmount);
        edtName=findViewById(R.id.edt_name);
        btnAdd=findViewById(R.id.btn_Add);
        DatabaseHelper databaseHelper=DatabaseHelper.getDB(MainScreen.this);

        btnAdd.setOnClickListener(view -> {
            databaseHelper.expanseDao().insert(new Expanse(edtName.getText().toString(),edtAmount.getText().toString()));
            ArrayList<Expanse> arrExpanse= (ArrayList<Expanse>) databaseHelper.expanseDao().getAllExpanse();
        for (int i=0;i<arrExpanse.size();i++){
            Log.d("Data",""+arrExpanse.get(i).getTittle());

        }


        });
    }
}