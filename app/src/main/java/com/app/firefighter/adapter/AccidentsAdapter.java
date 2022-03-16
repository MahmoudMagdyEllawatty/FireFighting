package com.app.firefighter.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.firefighter.R;
import com.app.firefighter.activities.department.AccidentDetailsActivity;
import com.app.firefighter.callback.AccidentCallback;
import com.app.firefighter.controller.AccidentController;
import com.app.firefighter.helper.LoadingHelper;
import com.app.firefighter.helper.SharedData;
import com.app.firefighter.model.Accident;

import java.util.ArrayList;


public class AccidentsAdapter extends RecyclerView.Adapter<AccidentsAdapter.ViewHolder> {

    private ArrayList<Accident> Accidents;
    private Context context;

    public AccidentsAdapter(ArrayList<Accident> Accidents, Context context) {
        this.Accidents = Accidents;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.accident_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Accident Accident = Accidents.get(position);

        holder.title.setText(Accident.getUserName());
        holder.notes.setText(Accident.getNotes());
        holder.department.setText(Accident.getDepartment().getName());
        holder.address.setText(Accident.getAddress());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.accident = Accident;
                Intent intent = new Intent(context, AccidentDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return Accidents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,address,department,notes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.user_name);
            address = itemView.findViewById(R.id.address);
            department = itemView.findViewById(R.id.department);
            notes = itemView.findViewById(R.id.notes);

        }
    }
}
