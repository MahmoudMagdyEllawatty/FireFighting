package com.app.firefighter.activities.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.firefighter.R;
import com.app.firefighter.callback.DepartmentCallback;
import com.app.firefighter.controller.DepartmentController;
import com.app.firefighter.helper.SharedData;
import com.app.firefighter.model.Department;

import java.util.ArrayList;

public class UserSelectSectionActivity extends AppCompatActivity {


    RecyclerView sectionsList;
    ArrayList<Department> departments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select_section);

        sectionsList = findViewById(R.id.sections);
        sectionsList.setLayoutManager(new GridLayoutManager(this,2));
        sectionsList.setItemAnimator(new DefaultItemAnimator());

        new DepartmentController().getDepartments(
                new DepartmentCallback() {
                    @Override
                    public void onSuccess(ArrayList<Department> complaints) {
                        departments = complaints;
                        sectionsList.setAdapter(new SectionsAdapter());
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                }
        );
    }




    class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(UserSelectSectionActivity.this)
                    .inflate(R.layout.section_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Department department = departments.get(position);

            holder.name.setText(department.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedData.department = department;
                    Intent intent = new Intent(UserSelectSectionActivity.this,
                            UserAddReportActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return departments.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
            }
        }

    }
}