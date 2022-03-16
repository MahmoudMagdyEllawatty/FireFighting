package com.app.firefighter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.firefighter.R;
import com.app.firefighter.helper.SharedData;
import com.app.firefighter.model.Complaint;

import java.util.ArrayList;


public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ViewHolder> {

    private ArrayList<Complaint> Complaints;
    private Context context;

    public ComplaintsAdapter(ArrayList<Complaint> Complaints, Context context) {
        this.Complaints = Complaints;
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
        Complaint Complaint = Complaints.get(position);

        holder.title.setText(Complaint.getName());
        holder.notes.setText(Complaint.getMessage());
        holder.department.setText(Complaint.getEmail());
        holder.address.setText(Complaint.getPhone());


    }
    @Override
    public int getItemCount() {
        return Complaints.size();
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
