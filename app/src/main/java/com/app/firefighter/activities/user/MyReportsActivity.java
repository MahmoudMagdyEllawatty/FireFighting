package com.app.firefighter.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.firefighter.R;
import com.app.firefighter.activities.department.AccidentsActivity;
import com.app.firefighter.adapter.AccidentsAdapter;
import com.app.firefighter.callback.AccidentCallback;
import com.app.firefighter.controller.AccidentController;
import com.app.firefighter.helper.SharedData;
import com.app.firefighter.model.Accident;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MyReportsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    ImageView imgNoProduct;
    TextView no_data_fount;
    private ShimmerFrameLayout mShimmerViewContainer;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("My Reports");



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

        new AccidentController()
                .getAccidents(new AccidentCallback() {
                    @Override
                    public void onSuccess(ArrayList<Accident> complaints) {
                        ArrayList<Accident> accidents = new ArrayList<>();
                        for (Accident accident : complaints){
                            if(accident.getUserName() == null)
                                continue;
                            if(accident.getUserName().equals(SharedData.currentPhone)){
                                accidents.add(accident);
                            }
                        }

                        if(accidents.isEmpty()){
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

                            recyclerView.setAdapter(new AccidentsAdapter(accidents, MyReportsActivity.this));
                        }

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });


    }

}