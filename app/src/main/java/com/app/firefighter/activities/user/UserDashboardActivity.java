package com.app.firefighter.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.firefighter.R;
import com.app.firefighter.activities.admin.AdminDashboardActivity;
import com.app.firefighter.activities.admin.ComplaintsActivity;
import com.app.firefighter.activities.admin.DepartmentsActivity;

public class UserDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);


        (findViewById(R.id.reports))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserDashboardActivity.this,
                                MyReportsActivity.class);
                        startActivity(intent);
                    }
                });

        (findViewById(R.id.complaints))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserDashboardActivity.this,
                                UserSendComplaintActivity.class);
                        startActivity(intent);
                    }
                });


        (findViewById(R.id.report))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserDashboardActivity.this,
                                UserSelectSectionActivity.class);
                        startActivity(intent);
                    }
                });
    }
}