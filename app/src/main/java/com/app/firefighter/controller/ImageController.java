package com.app.firefighter.controller;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.app.firefighter.callback.FileUploadCallback;
import com.app.firefighter.helper.FirebaseHelper;
import com.app.firefighter.model.Accident;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ImageController {

    FirebaseHelper<Accident> helper = new FirebaseHelper<>();

    public void uploadImage(Uri uri, final FileUploadCallback callback){
        helper.uploadDoc(uri.toString(),uri)
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            callback.onSuccess(task.getResult().toString());
                        }else{
                            callback.onFail(task.getException().toString());
                        }
                    }
                });
    }

}
