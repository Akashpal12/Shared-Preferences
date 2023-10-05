package com.example.myapiaplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyDataAdpater  extends RecyclerView.Adapter<MyDataAdpater.MyViewHolder> {
    Context context;
    List<OwnersModel>arryList;

    public MyDataAdpater(Context context, List<OwnersModel> arryList) {
        this.context = context;
        this.arryList = arryList;
    }

    @Override
    public MyDataAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_remedial_input_model,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataAdpater.MyViewHolder holder, int position) {
        holder.txt_1.setText(arryList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return arryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rl_text_1;
        TextView txt_1,txt_2,txt_3;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_1=itemView.findViewById(R.id.txt_1);
            txt_2=itemView.findViewById(R.id.txt_2);
            txt_3=itemView.findViewById(R.id.txt_3);
            imageView=itemView.findViewById(R.id.imageView);
            rl_text_1=itemView.findViewById(R.id.rl_text_1);

        }
    }
}
