package com.example.myapiaplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class SecondActivty extends AppCompatActivity {
    SessionConfig sessionConfig;
    List<MyModel> myModelList;
    List<MyModel> myNewModelList = new ArrayList<>();

    List<OwnersModel> ownersModelList = new ArrayList<>();
    List<MyTeacherModel> myTeacherModelList;
    List<String> myStringList;
    TextView txt_data;
    FloatingActionButton add_alarm_fab;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activty);
        sessionConfig = new SessionConfig(SecondActivty.this);
        myModelList = new ArrayList<>();
        myTeacherModelList = new ArrayList<>();
        myStringList = new ArrayList<>();
        txt_data = findViewById(R.id.txt_data);
        add_alarm_fab = findViewById(R.id.add_alarm_fab);
        recyclerView = findViewById(R.id.recycler_view);


        add_alarm_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();

            }
        });
        setData();


        ItemTouchHelper.SimpleCallback swipeToDeleteCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // We don't support moving items
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Handle swipe-to-delete
                int position = viewHolder.getAdapterPosition();
                RegistrationModel deletedItem = sessionConfig.getRegisterationList().get(position);

                // Remove the item from the list
                sessionConfig.getRegisterationList().remove(position);

                setData();

                // Show a Snackbar with an undo option
                Snackbar.make(recyclerView, "Item deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", v -> {


                            // User clicked "Undo," add the item back to the list
                            sessionConfig.getRegisterationList().add(position, deletedItem);
                            setData();
                        })
                        .show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);






        myModelList= sessionConfig.getMyModelList();
        myTeacherModelList= sessionConfig.getMyTeacherModelList();
        myStringList=sessionConfig.getMyStringList();
        myNewModelList=sessionConfig.getNewMyModelList();

        ownersModelList=sessionConfig.getOwnersModelList();
        DataSet();

        String json = new Gson().toJson(ownersModelList);
        JsonArray jsonArray = new Gson().toJsonTree(ownersModelList).getAsJsonArray();


        for (int i = 0; i < ownersModelList.size(); i++) {  // Do not use anywhere in case Json Array or List <=
            Toast.makeText(this, "" + ownersModelList.get(i).getOwnerNumber(), Toast.LENGTH_SHORT).show();
        }


         Toast.makeText(this, ""+json, Toast.LENGTH_SHORT).show();
       // txt_data.setText(json.toString());
        txt_data.setText(jsonArray.toString());
    }


    void DataSet(){
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(SecondActivty.this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(new MyDataAdpater(SecondActivty.this,ownersModelList));
    }


    private void showBottomSheetDialog() {
        EditText edtname,edtFatherName,edtMotherName,edtDateofBirth;
        Button btn_Save;
        ImageView cut_img;


        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.my_input_bottomsheet);
        bottomSheetDialog.setCanceledOnTouchOutside(false);

         cut_img =bottomSheetDialog.findViewById(R.id.cut_img);
         edtname=bottomSheetDialog.findViewById(R.id.edtname);
         edtFatherName=bottomSheetDialog.findViewById(R.id.edtFatherName);
         edtMotherName=bottomSheetDialog.findViewById(R.id.edtMotherName);
         edtDateofBirth=bottomSheetDialog.findViewById(R.id.edtDateofBirth);
         btn_Save=bottomSheetDialog.findViewById(R.id.btn_Save);

        cut_img.setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
        });

        btn_Save.setOnClickListener(view -> {



            if (edtname.getText().toString().isEmpty() || edtFatherName.getText().toString().isEmpty() || edtMotherName.getText().toString().isEmpty() || edtDateofBirth.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
            } else {
                bottomSheetDialog.dismiss();
                RegistrationModel registrationModel = new RegistrationModel();
                registrationModel.setName(edtname.getText().toString().trim());
                registrationModel.setFatherName(edtFatherName.getText().toString().trim());
                registrationModel.setMotherName(edtMotherName.getText().toString().trim());
                registrationModel.setDateofBirth(edtDateofBirth.getText().toString().trim());

                // Retrieve the existing list from session configuration
                List<RegistrationModel> existingList = sessionConfig.getRegisterationList();

                // Create a new list to hold the data
                List<RegistrationModel> newList = new ArrayList<>();

                // If the existing list is not empty, add its contents to the new list
                if (existingList != null && !existingList.isEmpty()) {
                    newList.addAll(existingList);
                }

                // Add the user-added data to the new list
                newList.add(registrationModel);

                // Update the session configuration with the new list
                sessionConfig.setRegisterationList(newList);
                edtname.setText("");
                edtFatherName.setText("");
                edtMotherName.setText("");
                edtDateofBirth.setText("");
                // Rest of your code

                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                setData();

            }

        });

        bottomSheetDialog.show();
    }


    private void setData() {
        try {

            GridLayoutManager manager = new GridLayoutManager(SecondActivty.this, 1, GridLayoutManager.VERTICAL, false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(manager);
            RegistrationAdapter listPloughingAdapter = new RegistrationAdapter(SecondActivty.this, sessionConfig.getRegisterationList());
            recyclerView.setAdapter(listPloughingAdapter);

            int resId = R.anim.layout_animation_fall_down;
           // int  resId = R.anim.layout_animation_slide_up;
          //  int   resId = R.anim.layout_animation_rotate_in;
          //  int   resId = R.anim.layout_animation_scale_in;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
            recyclerView.setLayoutAnimation(animation);
            listPloughingAdapter.notifyDataSetChanged();

            if (resId != 0) {
                // Set animation for RecyclerView
                LayoutAnimationController animation1 = AnimationUtils.loadLayoutAnimation(this, resId);
                recyclerView.setLayoutAnimation(animation1);
                listPloughingAdapter.notifyDataSetChanged();
            }


        } catch (Exception e) {
        }
    }

}