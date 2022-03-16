package com.app.firefighter.helper;


import com.app.firefighter.model.Accident;
import com.app.firefighter.model.Department;

import java.io.File;

public class SharedData {
    public static int userType = 0;
    public static File last_file;

    public static String currentPhone;
    public static Department department;
    public static Department loggedDepartment;
    public static int state;
    public static Accident accident;
}
