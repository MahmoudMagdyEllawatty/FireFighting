package com.app.firefighter.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.app.firefighter.R;
import com.app.firefighter.helper.SharedData;

public class MainActivity extends AppCompatActivity {

    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.etxt_phone);

        (findViewById(R.id.send))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(phone.getText() == null){
                            phone.setError("Required");
                            return;
                        }else if(phone.getText().toString().equals("")){
                            phone.setError("Required");
                            return;
                        }else if(!phone.getText().toString().matches("\\d+(?:\\.\\d+)?")){
                            phone.setError("Please Enter Valid Phone");
                            return;
                        }

                        SharedData.currentPhone = phone.getText().toString();
                        Intent intent = new Intent(MainActivity.this,UserDashboardActivity.class);
                        startActivity(intent);

                    }
                });

    }
}