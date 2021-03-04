package com.sd.saruj.personalallproject.MoreDataLoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sd.saruj.personalallproject.R;
import com.sd.saruj.personalallproject.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class MoreDataLoadActivity extends AppCompatActivity {
    String URL = "https://jsonplaceholder.typicode.com/posts?userId=" ;

    RecyclerView mRecyclerView;
    NestedScrollView mNestedScroll ;
    MoreDataLoadAdapter moreDataLoadAdapter ;
    ArrayList<MoreDataModel> mItemList ;

    int pageNumb = 1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_data_load);

        mRecyclerView = findViewById(R.id.recyclerview) ;
        mNestedScroll = findViewById(R.id.nested_scroll) ;
        mItemList = new ArrayList<>() ;

        initializeAdapter();


        loadProducts(1);
        loadNextPageList() ;

    }

    private void loadProducts(final int number) {
        AppController.getAppController().getInAppNotifier().log("number: ", number+"");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+number,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        AppController.getAppController().getInAppNotifier().log("response data: ", response);
                        try {
                            JSONArray array = new JSONArray(response);
//                            AppController.getAppController().getInAppNotifier().log("array: ", array.length()+"");


                            for(int i=0;i<array.length();i++){
                                JSONObject mCategoryObject = array.getJSONObject(i);
                                MoreDataModel aCategoryModel = new MoreDataModel() ;
                                Field[] fields =  aCategoryModel.getAllFields() ;

                                for(int j=0; j<fields.length; j++ ){
                                    String fieldName = fields[j].getName() ;
                                    String fieldValueInJson =mCategoryObject.has(fieldName)? mCategoryObject.getString(fieldName) : "" ;
                                    try{
                                        fields[j].set(aCategoryModel, fieldValueInJson) ;
                                    }catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                                mItemList.add(aCategoryModel) ;
                            }
                            moreDataLoadAdapter.notifyDataSetChanged();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void initializeAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        moreDataLoadAdapter = new MoreDataLoadAdapter(getApplicationContext(), mItemList){

            @Override
            public void loadNextPage(int pageNumber) {
                AppController.getAppController().getInAppNotifier().log("pageNumb: ", "act:"+pageNumber+"" );
                pageNumb = pageNumber ;
                moreDataLoadAdapter.setPageNumber(pageNumber);

            }
        } ;
        mRecyclerView.setAdapter(moreDataLoadAdapter);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void loadNextPageList() {
        if (mNestedScroll != null ) {
            mNestedScroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {

                    AppController.getAppController().getInAppNotifier().log("nested", "yes"+pageNumb);
                    loadProducts(pageNumb);

                }
            });
        }


    }

}