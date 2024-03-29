package com.example.myapiaplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListRemedialActivityAdapter extends RecyclerView.Adapter<ListRemedialActivityAdapter.MyHolder> {

    private Context context;
    List<OwnersModel> arrayList;


    public ListRemedialActivityAdapter(Context context, List <OwnersModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_remedial_input_model,null);
        MyHolder myHolder =new MyHolder(layout);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        try {
            if (position == 0) {
                holder.txt_1.setText("Name ");
                holder.txt_2.setText("Owner Name");
                holder.txt_3.setText("Owner Phone");

                holder.rl_text_1.setBackgroundColor(Color.parseColor("#000000"));
                holder.txt_1.setTextColor(Color.parseColor("#FFFFFF"));
                holder.txt_2.setTextColor(Color.parseColor("#FFFFFF"));
                holder.txt_3.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                final int pos = position - 1;
                holder.txt_1.setText(""+arrayList.get(pos).getName());
                holder.txt_2.setText(""+arrayList.get(pos).getOwnerName());
                holder.txt_3.setText(""+arrayList.get(pos).getOwnerNumber());
                holder.rl_text_1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.txt_1.setTextColor(Color.parseColor("#000000"));
                holder.txt_2.setTextColor(Color.parseColor("#000000"));
                holder.txt_3.setTextColor(Color.parseColor("#000000"));
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrayList.remove(pos);
                        //recycler.removeViewAt(position);
                        notifyItemRemoved(pos);
                        notifyItemRangeChanged(pos, arrayList.size()+1);
                    }
                });
            }
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    public static class MyHolder extends RecyclerView.ViewHolder
    {
        ///ImageView upload_image;
        LinearLayout rl_text_1;
        TextView txt_1,txt_2,txt_3;
        ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txt_1=itemView.findViewById(R.id.txt_1);
            txt_2=itemView.findViewById(R.id.txt_2);
            txt_3=itemView.findViewById(R.id.txt_3);
            imageView=itemView.findViewById(R.id.imageView);
            rl_text_1=itemView.findViewById(R.id.rl_text_1);
        }


    }

}
