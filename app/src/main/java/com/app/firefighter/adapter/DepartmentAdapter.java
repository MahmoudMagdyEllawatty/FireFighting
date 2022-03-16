package com.app.firefighter.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.firefighter.R;
import com.app.firefighter.callback.DepartmentCallback;
import com.app.firefighter.controller.DepartmentController;
import com.app.firefighter.helper.LoadingHelper;
import com.app.firefighter.model.Department;

import java.util.ArrayList;


public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    private ArrayList<Department> Departments;
    private Context context;

    public DepartmentAdapter(ArrayList<Department> Departments, Context context) {
        this.Departments = Departments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.category_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Department Department = Departments.get(position);

        holder.title.setText(Department.getName());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadingHelper(context)
                        .showDialog("Delete Department", "Are You Sure?", "Delete", "Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new DepartmentController().delete(Department, new DepartmentCallback() {
                                    @Override
                                    public void onSuccess(ArrayList<Department> Departments) {
                                        Toast.makeText(context, "Deleted!!", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFail(String msg) {

                                    }
                                });
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddStationDialog(Department);
            }
        });

    }

    private void showAddStationDialog(Department Department){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View dialogView =  LayoutInflater.from(context).inflate(R.layout.dialog_station, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);

        final Button dialogBtnSubmit = dialogView.findViewById(R.id.btn_submit);
        final ImageButton dialogBtnClose = dialogView.findViewById(R.id.btn_close);
        final EditText etxtTitle = dialogView.findViewById(R.id.etxt_title);
        final EditText etxtPhone = dialogView.findViewById(R.id.etxt_phone);
        final EditText etxtUserName = dialogView.findViewById(R.id.etxt_user_name);
        final EditText etxtPassword = dialogView.findViewById(R.id.etxt_password);
        final TextView title = dialogView.findViewById(R.id.title);

        etxtTitle.setText(Department.getName());
        etxtPassword.setText(Department.getPassword());
        etxtPhone.setText(Department.getPhone());
        etxtUserName.setText(Department.getUserName());

        title.setText("Update "+Department.getName());


        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        dialogBtnSubmit.setOnClickListener(v -> {

            if(etxtTitle.getText() == null){
                etxtTitle.setError(context.getString(R.string.required));
                return;
            }else if(etxtTitle.getText().toString().equals("")){
                etxtTitle.setError(context.getString(R.string.required));
                return;
            }

            if(etxtPassword.getText() == null){
                etxtPassword.setError(context.getString(R.string.required));
                return;
            }else if(etxtPassword.getText().toString().equals("")){
                etxtPassword.setError(context.getString(R.string.required));
                return;
            }

            if(etxtPhone.getText() == null){
                etxtPhone.setError(context.getString(R.string.required));
                return;
            }else if(etxtPhone.getText().toString().equals("")){
                etxtPhone.setError(context.getString(R.string.required));
                return;
            }

            if(etxtUserName.getText() == null){
                etxtUserName.setError(context.getString(R.string.required));
                return;
            }else if(etxtUserName.getText().toString().equals("")){
                etxtUserName.setError(context.getString(R.string.required));
                return;
            }




            Department.setName(etxtTitle.getText().toString());
            Department.setPassword(etxtPassword.getText().toString());
            Department.setPhone(etxtPhone.getText().toString());
            Department.setUserName(etxtUserName.getText().toString());

            new DepartmentController()
                    .Save(Department, new DepartmentCallback() {
                        @Override
                        public void onSuccess(ArrayList<Department> policies) {
                            alertDialog.dismiss();
                            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(String msg) {
                            alertDialog.dismiss();
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                    });


        });


        dialogBtnClose.setOnClickListener(v -> alertDialog.dismiss());
    }

    @Override
    public int getItemCount() {
        return Departments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView delete,edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            delete = itemView.findViewById(R.id.img_delete);
            edit = itemView.findViewById(R.id.img_edit);

        }
    }
}
