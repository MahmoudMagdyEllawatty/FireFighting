package com.app.firefighter.callback;

import com.app.firefighter.model.Complaint;
import com.app.firefighter.model.Department;

import java.util.ArrayList;

public interface DepartmentCallback {
    void onSuccess(ArrayList<Department> complaints);
    void onFail(String msg);
}
