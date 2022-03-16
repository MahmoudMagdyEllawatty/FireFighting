package com.app.firefighter.callback;

import com.app.firefighter.model.Accident;
import com.app.firefighter.model.Complaint;

import java.util.ArrayList;

public interface AccidentCallback {
    void onSuccess(ArrayList<Accident> complaints);
    void onFail(String msg);
}
