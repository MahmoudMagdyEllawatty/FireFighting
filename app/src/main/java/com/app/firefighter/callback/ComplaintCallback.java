package com.app.firefighter.callback;

import com.app.firefighter.model.Complaint;

import java.util.ArrayList;

public interface ComplaintCallback {
    void onSuccess(ArrayList<Complaint> complaints);
    void onFail(String msg);
}
