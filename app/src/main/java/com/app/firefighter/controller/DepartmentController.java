package com.app.firefighter.controller;

import androidx.annotation.NonNull;

import com.app.firefighter.callback.DepartmentCallback;
import com.app.firefighter.helper.Config;
import com.app.firefighter.helper.FirebaseHelper;
import com.app.firefighter.model.Department;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class DepartmentController {
    private final String node = Config.Department_NODE;
    ArrayList<Department> users = new ArrayList<>();
    FirebaseHelper<Department> helper = new FirebaseHelper<Department>();

    public void Save(final Department user, final DepartmentCallback callback){
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

    public void delete(Department trainingType, DepartmentCallback callback){
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

    public void getDepartments(final DepartmentCallback callback){
        helper.getAll(node, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots == null && e != null){
                    callback.onFail(e.getLocalizedMessage());
                }else {
                    ArrayList<Department> users = new ArrayList<>();
                    assert queryDocumentSnapshots != null;
                    for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {
                        Department user = snap.toObject(Department.class);
                        users.add(user);
                    }
                    callback.onSuccess(users);
                }
            }
        });
    }
}
