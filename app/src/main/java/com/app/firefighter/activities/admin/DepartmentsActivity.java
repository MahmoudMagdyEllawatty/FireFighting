package com.app.firefighter.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.firefighter.R;
import com.app.firefighter.adapter.DepartmentAdapter;
import com.app.firefighter.callback.DepartmentCallback;
import com.app.firefighter.controller.DepartmentController;
import com.app.firefighter.model.Department;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DepartmentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    ImageView imgNoProduct;
    TextView no_data_fount;
    private ShimmerFrameLayout mShimmerViewContainer;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FloatingActionButton add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle(R.string.departments);


        add = findViewById(R.id.fab_add);
        no_data_fount = findViewById(R.id.no_data_fount);
        recyclerView = findViewById(R.id.cart_recyclerview);
        imgNoProduct = findViewById(R.id.image_no_product);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mSwipeRefreshLayout =findViewById(R.id.swipeToRefresh);
        //set color of swipe refresh
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple_700);

        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);

        //swipe refresh listeners
        mSwipeRefreshLayout.setOnRefreshListener(() -> {

            loadData();
            //after shuffle id done then swife refresh is off
            mSwipeRefreshLayout.setRefreshing(false);
        });


        loadData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddStationDialog();
            }
        });
    }


    private void showAddStationDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(DepartmentsActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_station, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);

        final Button dialogBtnSubmit = dialogView.findViewById(R.id.btn_submit);
        final ImageButton dialogBtnClose = dialogView.findViewById(R.id.btn_close);

        final EditText etxtTitle = dialogView.findViewById(R.id.etxt_title);
        final EditText etxtPhone = dialogView.findViewById(R.id.etxt_phone);
        final EditText etxtEmail = dialogView.findViewById(R.id.etxt_email);
        final EditText etxtUserName = dialogView.findViewById(R.id.etxt_user_name);
        final EditText etxtPassword = dialogView.findViewById(R.id.etxt_password);

        final TextView title = dialogView.findViewById(R.id.title);


        title.setText(R.string.add_department);


        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        dialogBtnSubmit.setOnClickListener(v -> {

            if(etxtTitle.getText() == null){
                etxtTitle.setError(getString(R.string.required));
                return;
            }else if(etxtTitle.getText().toString().equals("")){
                etxtTitle.setError(getString(R.string.required));
                return;
            }

            if(etxtPassword.getText() == null){
                etxtPassword.setError(getString(R.string.required));
                return;
            }else if(etxtPassword.getText().toString().equals("")){
                etxtPassword.setError(getString(R.string.required));
                return;
            }

            if(etxtPhone.getText() == null){
                etxtPhone.setError(getString(R.string.required));
                return;
            }else if(etxtPhone.getText().toString().equals("")){
                etxtPhone.setError(getString(R.string.required));
                return;
            }

            if(etxtUserName.getText() == null){
                etxtUserName.setError(getString(R.string.required));
                return;
            }else if(etxtUserName.getText().toString().equals("")){
                etxtUserName.setError(getString(R.string.required));
                return;
            }


            if(etxtEmail.getText() == null){
                etxtEmail.setError(getString(R.string.required));
                return;
            }else if(etxtEmail.getText().toString().equals("")){
                etxtEmail.setError(getString(R.string.required));
                return;
            }


            Department department = new Department();
            department.setKey("");
            department.setName(etxtTitle.getText().toString());
            department.setPassword(etxtPassword.getText().toString());
            department.setPhone(etxtPhone.getText().toString());
            department.setUserName(etxtUserName.getText().toString());
            department.setEmail(etxtEmail.getText().toString());


            new DepartmentController()
                    .Save(department, new DepartmentCallback() {
                        @Override
                        public void onSuccess(ArrayList<Department> complaints) {
                            Toast.makeText(DepartmentsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }

                        @Override
                        public void onFail(String msg) {
                            Toast.makeText(DepartmentsActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

        });


        dialogBtnClose.setOnClickListener(v -> alertDialog.dismiss());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadData(){
        new DepartmentController().getDepartments(new DepartmentCallback() {
            @Override
            public void onSuccess(ArrayList<Department> banks) {
                if(banks.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setVisibility(View.GONE);
                    no_data_fount.setVisibility(View.VISIBLE);
                    no_data_fount.setText(R.string.no_result_found);
                    imgNoProduct.setVisibility(View.VISIBLE);
                    imgNoProduct.setImageResource(R.drawable.not_found);
                    //Stopping Shimmer Effects
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                }else{
                    //Stopping Shimmer Effects
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                    mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    imgNoProduct.setVisibility(View.GONE);
                    no_data_fount.setVisibility(View.GONE);

                    recyclerView.setAdapter(new DepartmentAdapter(banks, DepartmentsActivity.this));
                }
            }

            @Override
            public void onFail(String msg) {
                //Stopping Shimmer Effects
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);

                recyclerView.setVisibility(View.VISIBLE);
                imgNoProduct.setVisibility(View.GONE);
                Toast.makeText(DepartmentsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}