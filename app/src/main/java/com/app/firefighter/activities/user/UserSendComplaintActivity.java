package com.app.firefighter.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.firefighter.R;
import com.app.firefighter.callback.ComplaintCallback;
import com.app.firefighter.controller.ComplaintController;
import com.app.firefighter.helper.SharedData;
import com.app.firefighter.model.Complaint;

import java.util.ArrayList;

import io.grpc.SynchronizationContext;

public class UserSendComplaintActivity extends AppCompatActivity {

    EditText name,email,msg;
    ImageView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_send_complaint);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        msg = findViewById(R.id.msg);
        send = findViewById(R.id.login);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText() == null){
                    name.setError("Required");
                    return;
                }else if(name.getText().toString().equals("")){
                    name.setError("Required");
                    return;
                }


                if(email.getText() == null){
                    email.setError("Required");
                    return;
                }else if(email.getText().toString().equals("")){
                    email.setError("Required");
                    return;
                }


                if(msg.getText() == null){
                    msg.setError("Required");
                    return;
                }else if(msg.getText().toString().equals("")){
                    msg.setError("Required");
                    return;
                }


                Complaint complaint = new Complaint();
                complaint.setEmail(email.getText().toString());
                complaint.setKey("");
                complaint.setMessage(msg.getText().toString());
                complaint.setName(name.getText().toString());
                complaint.setPhone(SharedData.currentPhone);


                new ComplaintController().Save(complaint, new ComplaintCallback() {
                    @Override
                    public void onSuccess(ArrayList<Complaint> complaints) {
                        Toast.makeText(UserSendComplaintActivity.this, "Complaint Sent Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserSendComplaintActivity.this,UserDashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });


            }
        });
    }
}