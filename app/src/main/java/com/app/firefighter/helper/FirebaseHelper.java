package com.app.firefighter.helper;

import android.app.Activity;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseHelper<T> {


    public void createAccount(String email, String password,
                              Activity activity,
                              OnCompleteListener<AuthResult> listener){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(activity,listener);
    }

//   public void updateAccount(String oldEmail, String oldPassword, final Employer employer, Activity activity,
//                             final OnCompleteListener<Void> listener){
//       FirebaseAuth mAuth = FirebaseAuth.getInstance();
//       mAuth.signInWithEmailAndPassword(oldEmail,oldPassword)
//               .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
//                   @Override
//                   public void onComplete(@NonNull Task<AuthResult> task) {
//                       if(task.isSuccessful()){
//                           final FirebaseUser user = task.getResult().getUser();
//                           user.updateEmail(employer.getDescription())
//                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                       @Override
//                                       public void onComplete(@NonNull Task<Void> task) {
//                                            if(task.isSuccessful()){
//                                                user.updatePassword(employer.getPassword())
//                                                        .addOnCompleteListener(listener);
//                                            }
//                                       }
//                                   });
//                       }
//                   }
//               });
//   }

    public void login(String email, String password,
                      Activity activity,
                      OnCompleteListener<AuthResult> listener){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(activity,listener);
    }

    public String getKey(String node){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        return database.collection(node).document().getId();
    }


    public Task<Void> save(String node, String key, T data){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        return database.collection(node)
                .document(key)
                .set(data);
    }

    public Task<Void> delete(String node, String key){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        return  database.collection(node)
                .document(key)
                .delete();
    }

    public void getAll(String node, EventListener<QuerySnapshot> snapshotEventListener){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(node).addSnapshotListener(snapshotEventListener);
    }

    public Task<QuerySnapshot> getAllOnce(String node){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        return database.collection(node).get();
    }

    public CollectionReference getAllQuery(String node){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        return database.collection(node);
    }



    public Task<Uri> uploadDoc(String name, Uri doc){
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        final StorageReference docRef = storageRef.child(name +"/"+ doc.toString());
        return docRef.putFile(doc)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw task.getException();
                        }
                        return docRef.getDownloadUrl();
                    }
                });
    }


}
