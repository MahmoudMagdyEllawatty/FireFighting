package com.app.firefighter.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.firefighter.R;
import com.app.firefighter.adapter.DepartmentAdapter;

import java.io.InterruptedIOException;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        (findViewById(R.id.complaints))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdminDashboardActivity.this,ComplaintsActivity.class);
                        startActivity(intent);
                    }
                });


        (findViewById(R.id.sections))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdminDashboardActivity.this, DepartmentsActivity.class);
                        startActivity(intent);
                    }
                });
    }
}