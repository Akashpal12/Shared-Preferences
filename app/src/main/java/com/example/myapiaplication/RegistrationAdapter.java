package com.example.myapiaplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.MyViewHolder> {
    Context context;
    List<RegistrationModel> arryList;
    SessionConfig sessionConfig;

    public RegistrationAdapter(Context context, List<RegistrationModel> arryList) {
        this.context = context;
        this.arryList = arryList;
        sessionConfig = new SessionConfig(context);
    }

    @Override
    public RegistrationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_itemslist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RegistrationAdapter.MyViewHolder holder, int position) {

        if (position == 0) {
            holder.mainLayout.setVisibility(View.GONE);
            holder.mycard_view.setVisibility(View.GONE);

        } else {
            final int pos = position - 1;

            holder.mainLayout.setVisibility(View.VISIBLE);
            holder.mycard_view.setVisibility(View.VISIBLE);
            holder.card_id.setText(String.valueOf(pos + 1));
            holder.edtname.setText(arryList.get(pos).getName());
            holder.edtFatherName.setText(arryList.get(pos).getFatherName());
            holder.edtMotherName.setText(arryList.get(pos).getMotherName());
            holder.edtDateofBirth.setText(arryList.get(pos).getDateofBirth());

            holder.btn_delete.setOnClickListener(view -> {
                deleteDialogue(pos);
            });

            holder.btn_update.setOnClickListener(view -> {
                updateDialogue(pos);
            });
        }


    }
    void deleteDialogue(int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are You Sure")
                .setMessage("Are You want to sure Delete ")
                .setPositiveButton("OK", (dialog, which) -> {
                    arryList.remove(pos);
                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos, arryList.size() + 1);
                    sessionConfig.setRegisterationList(arryList);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                })
                .show();

    }
    void updateDialogue(int pos){
        EditText edtname, edtFatherName, edtMotherName, edtDateofBirth;
        Button btn_Save;
        ImageView cut_img;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customLayout = inflater.inflate(R.layout.my_input_bottomsheet, null);
        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        cut_img = customLayout.findViewById(R.id.cut_img);
        edtname = customLayout.findViewById(R.id.edtname);
        edtFatherName = customLayout.findViewById(R.id.edtFatherName);
        edtMotherName = customLayout.findViewById(R.id.edtMotherName);
        edtDateofBirth = customLayout.findViewById(R.id.edtDateofBirth);
        btn_Save = customLayout.findViewById(R.id.btn_Save);

        edtname.setText(arryList.get(pos).getName());
        edtFatherName.setText(arryList.get(pos).getFatherName());
        edtMotherName.setText(arryList.get(pos).getMotherName());
        edtDateofBirth.setText(arryList.get(pos).getDateofBirth());

        cut_img.setOnClickListener(view12 -> alertDialog.dismiss());
        btn_Save.setText("Update");
        btn_Save.setOnClickListener(view1 -> {

            String newName = edtname.getText().toString().trim();
            String newFatherName = edtFatherName.getText().toString().trim();
            String newMotherName = edtMotherName.getText().toString().trim();
            String newDateOfBirth = edtDateofBirth.getText().toString().trim();

            // Update the item in the list
            if (pos >= 0 && pos < arryList.size()) {
                RegistrationModel updatedModel = arryList.get(pos);
                updatedModel.setName(newName);
                updatedModel.setFatherName(newFatherName);
                updatedModel.setMotherName(newMotherName);
                updatedModel.setDateofBirth(newDateOfBirth);

                // Update the list with the modified item
                arryList.set(pos, updatedModel);

                // Save the updated list to shared preferences
                sessionConfig.setRegisterationList(arryList);

                // Notify the adapter of the data change
                notifyDataSetChanged();
            }

            alertDialog.dismiss();


        });

    }


    @Override
    public int getItemCount() {
        return arryList.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView edtname, edtFatherName, edtMotherName, edtDateofBirth, card_id;
        Button btn_delete, btn_update;
        LinearLayout mainLayout;
        CardView mycard_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            edtname = itemView.findViewById(R.id.edtname);
            edtFatherName = itemView.findViewById(R.id.edtFatherName);
            edtMotherName = itemView.findViewById(R.id.edtMotherName);
            edtDateofBirth = itemView.findViewById(R.id.edtDateofBirth);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_update = itemView.findViewById(R.id.btn_update);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            mycard_view = itemView.findViewById(R.id.mycard_view);
            card_id = itemView.findViewById(R.id.card_id);

        }
    }
}
