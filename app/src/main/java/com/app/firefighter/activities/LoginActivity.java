package com.app.firefighter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.firefighter.R;
import com.app.firefighter.activities.admin.AdminDashboardActivity;
import com.app.firefighter.activities.department.DepartmentDashboardActivity;
import com.app.firefighter.activities.user.MainActivity;
import com.app.firefighter.callback.DepartmentCallback;
import com.app.firefighter.controller.DepartmentController;
import com.app.firefighter.helper.SharedData;
import com.app.firefighter.model.Department;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    ImageView login;
    TextView user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        user = findViewById(R.id.user);


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText() == null){
                    email.setError("Required");
                    return;
                }else if(email.getText().toString().equals("")){
                    email.setError("Required");
                    return;
                }


                if(password.getText() == null){
                    password.setError("Required");
                    return;
                }else if(password.getText().toString().equals("")){
                    password.setError("Required");
                    return;
                }


                if(email.getText().toString().equals("admin@fire.com") && password.getText().toString().equals("123456")){
                    Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    new DepartmentController().getDepartments(new DepartmentCallback() {
                        @Override
                        public void onSuccess(ArrayList<Department> complaints) {
                            Department department = new Department();
                            department.setKey("Not Exist");
                            for (Department department1 : complaints){
                                if(department1.getUserName().equals(email.getText().toString()) &&
                                        department1.getPassword().equals(password.getText().toString())){
                                    department = department1;
                                    break;
                                }
                            }

                            if(department.getKey().equals("Not Exist")){
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }else{
                                SharedData.loggedDepartment = department;
                                Intent intent = new Intent(LoginActivity.this, DepartmentDashboardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFail(String msg) {
                            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}