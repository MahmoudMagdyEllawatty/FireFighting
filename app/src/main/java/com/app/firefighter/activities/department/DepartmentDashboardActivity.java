package com.app.firefighter.activities.department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.firefighter.R;
import com.app.firefighter.helper.SharedData;

public class DepartmentDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_dashboard);

        (findViewById(R.id.new_reports))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedData.state = 0;
                        Intent intent = new Intent(DepartmentDashboardActivity.this,AccidentsActivity.class);
                        startActivity(intent);
                    }
                });


        (findViewById(R.id.finished_reports))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedData.state = 1;
                        Intent intent = new Intent(DepartmentDashboardActivity.this,AccidentsActivity.class);
                        startActivity(intent);
                    }
                });
    }
}