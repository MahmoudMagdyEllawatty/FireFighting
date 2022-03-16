package com.app.firefighter.controller;

import androidx.annotation.NonNull;

import com.app.firefighter.callback.ComplaintCallback;
import com.app.firefighter.helper.Config;
import com.app.firefighter.helper.FirebaseHelper;
import com.app.firefighter.model.Complaint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class ComplaintController {
    private final String node = Config.Complaint_NODE;
    ArrayList<Complaint> users = new ArrayList<>();
    FirebaseHelper<Complaint> helper = new FirebaseHelper<Complaint>();

    public void Save(final Complaint user, final ComplaintCallback callback){
        if(user.getKey().equals("")){
            user.setKey(helper.getKey(node));
        }

        helper.save(node,user.getKey(),user)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            users.add(user);
                            callback.onSuccess(users);
                        }else{
                            callback.onFail(task.getException().toString());
                        }
                    }
                });
    }

    public void delete(Complaint trainingType, ComplaintCallback callback){
        helper.delete(node, trainingType.getKey())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            users = new ArrayList<>();
                            callback.onSuccess(users);
                        }else{
                            callback.onFail(task.getException().toString());
                        }
                    }
                });
    }

    public void getComplaints(final ComplaintCallback callback){
        helper.getAll(node, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots == null && e != null){
                    callback.onFail(e.getLocalizedMessage());
                }else {
                    ArrayList<Complaint> users = new ArrayList<>();
                    assert queryDocumentSnapshots != null;
                    for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {
                        Complaint user = snap.toObject(Complaint.class);
                        users.add(user);
                    }
                    callback.onSuccess(users);
                }
            }
        });
    }
}
